package com.example.JSON.CarDealer.Service.Impl;

import com.example.JSON.CarDealer.DTO.ImportCarsDto;
import com.example.JSON.CarDealer.DTO.ImportCustomersDto;
import com.example.JSON.CarDealer.DTO.ImportPartsDto;
import com.example.JSON.CarDealer.DTO.ImportSuppliersDto;
import com.example.JSON.CarDealer.Repository.*;
import com.example.JSON.CarDealer.Service.SeedService;
import com.example.JSON.CarDealer.entities.*;
import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private final static String RESOURCES_PATH = "src//main//resources//files//";
    private final static String CARS_PATH = "cars.json";
    private final static String SUPPLIERS_PATH = "suppliers.json";
    private final static String PARTS_PATH = "parts.json";
    private final static String CUSTOMERS_PATH = "customers.json";

    private CarRepository carRepository;
    private SupplierRepository supplierRepository;
    private PartRepository partRepository;
    private CustomerRepository customerRepository;
    private SaleRepository saleRepository;

    private ModelMapper modelMapper;
    private Gson gson;

    @Autowired
    public SeedServiceImpl(CarRepository carRepository, SupplierRepository supplierRepository, PartRepository partRepository, CustomerRepository customerRepository, SaleRepository saleRepository) {
        this.carRepository = carRepository;
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;

        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        return LocalDateTime.parse(jsonElement.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                    }
                })
                .create();
    }

    @Override
    public void seedCars() throws FileNotFoundException {
        FileReader fileReader = new FileReader(RESOURCES_PATH + CARS_PATH);

        ImportCarsDto[] carsInfo = this.gson.fromJson(fileReader, ImportCarsDto[].class);

        List<Car> listCars = Arrays.stream(carsInfo).map(carDto -> this.modelMapper.map(carDto, Car.class))
                .map(this::setRandomParts)
                .collect(Collectors.toList());

        this.carRepository.saveAll(listCars);
    }

    @Override
    public void seedCustomers() throws FileNotFoundException {
        FileReader fileReader = new FileReader(RESOURCES_PATH + CUSTOMERS_PATH);

        ImportCustomersDto[] customersInfo = this.gson.fromJson(fileReader, ImportCustomersDto[].class);

        List<Customer> listCustomers = Arrays.stream(customersInfo).map(customerDto -> this.modelMapper.map(customerDto, Customer.class)).collect(Collectors.toList());

        this.customerRepository.saveAll(listCustomers);
    }

    @Override
    public void seedParts() throws FileNotFoundException {
        FileReader fileReader = new FileReader(RESOURCES_PATH + PARTS_PATH);

        ImportPartsDto[] partsInfo = this.gson.fromJson(fileReader, ImportPartsDto[].class);

        List<Part> listParts = Arrays.stream(partsInfo).map(partDto -> this.modelMapper.map(partDto, Part.class))
                .map(this::setRandomSupplier)
                .collect(Collectors.toList());

        this.partRepository.saveAll(listParts);
    }

    @Override
    public void seedSuppliers() throws FileNotFoundException {
        FileReader fileReader = new FileReader(RESOURCES_PATH + SUPPLIERS_PATH);

        ImportSuppliersDto[] suppliersInfo = this.gson.fromJson(fileReader, ImportSuppliersDto[].class);

        List<Supplier> listSuppliers = Arrays.stream(suppliersInfo).map(supplierDto -> this.modelMapper.map(supplierDto, Supplier.class)).collect(Collectors.toList());

        this.supplierRepository.saveAll(listSuppliers);
    }

    @Override
    public void seedSale() {
        List<Sale> sales = setRandomCarAndCustomer();

        this.saleRepository.saveAll(sales);
    }

    private Optional<Customer> getRandomCustomer() {
        long countCustomer = this.customerRepository.count();

        int randomId = new Random().nextInt((int) countCustomer) + 1;

        return this.customerRepository.findById(randomId);
    }

    private Optional<Car> getRandomCar() {
        long countCar = this.carRepository.count();

        int randomId = new Random().nextInt((int) countCar) + 1;

        return this.carRepository.findById(randomId);
    }

    private Optional<Supplier> getRandomSupplier() {
        long countSuppliers = this.supplierRepository.count();

        int randomId = new Random().nextInt((int) countSuppliers) + 1;

        return this.supplierRepository.findById(randomId);
    }

    private Car setRandomParts(Car car) {
        long countParts = this.partRepository.count();

        int parts = new Random().nextInt((int) countParts);

        Set<Part> listParts = new LinkedHashSet<>();

        if (parts >= 3 && parts <= 5) {
            for (int i = 0; i < parts; i++) {
                int randomId = new Random().nextInt((int) countParts) + 1;

                Optional<Part> randomParts = this.partRepository.findById(randomId);

                listParts.add(randomParts.get());
            }
        }

        car.setParts(listParts);

        return car;
    }

    private Part setRandomSupplier(Part part) {
        Optional<Supplier> randomSupplier = getRandomSupplier();

        part.setSupplier(randomSupplier.get());

        return part;
    }

    private List<Sale> setRandomCarAndCustomer() {
        Sale saleCar = new Sale();
        Sale saleCustomer = new Sale();
        Sale saleDiscount = new Sale();

        List<Sale> listSale = new ArrayList<>();

        Car car1 = getRandomCar().get();
        Car car2 = getRandomCar().get();
        Car car3 = getRandomCar().get();

        Customer customer1 = getRandomCustomer().get();
        Customer customer2 = getRandomCustomer().get();
        Customer customer3 = getRandomCustomer().get();

        saleCar.setCar(car1);
        saleCar.setCustomer(customer1);
        saleCar.setDiscountPercentage(10);
        saleCustomer.setCar(car2);
        saleCustomer.setCustomer(customer2);
        saleCustomer.setDiscountPercentage(20);
        saleDiscount.setCar(car3);
        saleDiscount.setCustomer(customer3);
        saleDiscount.setDiscountPercentage(30);

        listSale.add(saleCar);
        listSale.add(saleCustomer);
        listSale.add(saleDiscount);

        return listSale;
    }
}
