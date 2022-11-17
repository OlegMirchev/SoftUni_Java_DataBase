package com.example.XML.CarDealer;

import com.example.XML.CarDealer.DTO.CarsWithMakeToyotaDto;
import com.example.XML.CarDealer.DTO.CustomersOrdersByBirthDateDto;
import com.example.XML.CarDealer.DTO.SuppliersNotThereNotThereSupplyAbroadDto;
import com.example.XML.CarDealer.Service.CarService;
import com.example.XML.CarDealer.Service.CustomerService;
import com.example.XML.CarDealer.Service.SeedService;
import com.example.XML.CarDealer.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private SeedService seedService;
    private CustomerService customerService;
    private CarService carService;
    private SupplierService supplierService;

    @Autowired
    public CommandLineRunnerImpl(SeedService seedService, CustomerService customerService, CarService carService, SupplierService supplierService) {
        this.seedService = seedService;
        this.customerService = customerService;
        this.carService = carService;
        this.supplierService = supplierService;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedAll();

//        Query 1 – Ordered Customers
//        selectAllCustomersOrderedBirthDay();

//        Query 2 – Cars from Make Toyota
//        selectMakeToyotaModels();

//        Query 3 – Local Suppliers
//        selectAllSuppliersThatNotImportFromAbroad();

    }

    private void selectAllSuppliersThatNotImportFromAbroad() throws JAXBException {
        SuppliersNotThereNotThereSupplyAbroadDto suppliers = this.supplierService.selectAllSuppliersWhichNotThereSupplyAbroad();

        JAXBContext context = JAXBContext.newInstance(SuppliersNotThereNotThereSupplyAbroadDto.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(suppliers, System.out);
    }

    private void selectMakeToyotaModels() throws JAXBException {
        CarsWithMakeToyotaDto cars = this.carService.selectAllCarsWithMakeToyota();

        JAXBContext context = JAXBContext.newInstance(CarsWithMakeToyotaDto.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(cars, System.out);
    }

    private void selectAllCustomersOrderedBirthDay() throws JAXBException {
        CustomersOrdersByBirthDateDto customers = this.customerService.selectAllCustomersOrderByBirthDate();

        JAXBContext context = JAXBContext.newInstance(CustomersOrdersByBirthDateDto.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(customers, System.out);
    }

}
