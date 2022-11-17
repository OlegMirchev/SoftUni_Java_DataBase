package com.example.XML.CarDealer.Service.Impl;

import com.example.XML.CarDealer.DTO.ImportCarsDto;
import com.example.XML.CarDealer.DTO.ImportCustomersDto;
import com.example.XML.CarDealer.DTO.ImportPartsDto;
import com.example.XML.CarDealer.DTO.ImportSuppliersDto;
import com.example.XML.CarDealer.Repository.*;
import com.example.XML.CarDealer.Service.SeedService;
import com.example.XML.CarDealer.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private final static String RESOURCES_PATH = "src//main//resources//files//";
    private final static String CARS_PATH = "cars.xml";
    private final static String SUPPLIERS_PATH = "suppliers.xml";
    private final static String PARTS_PATH = "parts.xml";
    private final static String CUSTOMERS_PATH = "customers.xml";

    private CarRepository carRepository;
    private SupplierRepository supplierRepository;
    private PartRepository partRepository;
    private CustomerRepository customerRepository;
    private SaleRepository saleRepository;

    private ModelMapper modelMapper;

    @Autowired
    public SeedServiceImpl(CarRepository carRepository, SupplierRepository supplierRepository, PartRepository partRepository, CustomerRepository customerRepository, SaleRepository saleRepository) {
        this.carRepository = carRepository;
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;

        this.modelMapper = new ModelMapper();
    }

    @Override
    public void seedCars() throws FileNotFoundException, JAXBException {
        FileReader fileReader = new FileReader(RESOURCES_PATH + CARS_PATH);

        JAXBContext context = JAXBContext.newInstance(ImportCarsDto.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        ImportCarsDto unmarshal = (ImportCarsDto) unmarshaller.unmarshal(fileReader);

        List<Car> importCars = unmarshal.getCars().stream()
                .map(c -> this.modelMapper.map(c, Car.class))
                .map(this::setRandomParts)
                .collect(Collectors.toList());

        this.carRepository.saveAll(importCars);
    }

    @Override
    public void seedCustomers() throws FileNotFoundException, JAXBException {
        FileReader fileReader = new FileReader(RESOURCES_PATH + CUSTOMERS_PATH);

        JAXBContext context = JAXBContext.newInstance(ImportCustomersDto.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        ImportCustomersDto unmarshal = (ImportCustomersDto) unmarshaller.unmarshal(fileReader);

        List<Customer> importCustomers = unmarshal.getCustomers().stream().map(c -> this.modelMapper.map(c, Customer.class)).collect(Collectors.toList());

        this.customerRepository.saveAll(importCustomers);
    }

    @Override
    public void seedParts() throws FileNotFoundException, JAXBException {
        FileReader fileReader = new FileReader(RESOURCES_PATH + PARTS_PATH);

        JAXBContext context = JAXBContext.newInstance(ImportPartsDto.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        ImportPartsDto unmarshal = (ImportPartsDto) unmarshaller.unmarshal(fileReader);

        List<Part> importParts = unmarshal.getParts().stream()
                .map(p -> this.modelMapper.map(p, Part.class))
                .map(this::setRandomSupplier)
                .collect(Collectors.toList());

        this.partRepository.saveAll(importParts);
    }

    @Override
    public void seedSuppliers() throws FileNotFoundException, JAXBException {
        FileReader fileReader = new FileReader(RESOURCES_PATH + SUPPLIERS_PATH);

        JAXBContext context = JAXBContext.newInstance(ImportSuppliersDto.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        ImportSuppliersDto unmarshal = (ImportSuppliersDto) unmarshaller.unmarshal(fileReader);

        List<Supplier> importSuppliers = unmarshal.getSuppliers().stream().map(s -> this.modelMapper.map(s, Supplier.class)).collect(Collectors.toList());

        this.supplierRepository.saveAll(importSuppliers);
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

        if (parts >= 10 && parts <= 20) {
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
