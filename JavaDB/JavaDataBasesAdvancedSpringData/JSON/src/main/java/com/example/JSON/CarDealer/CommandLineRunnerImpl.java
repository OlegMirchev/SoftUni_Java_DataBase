package com.example.JSON.CarDealer;

import com.example.JSON.CarDealer.DTO.*;
import com.example.JSON.CarDealer.Service.CarService;
import com.example.JSON.CarDealer.Service.CustomerService;
import com.example.JSON.CarDealer.Service.SeedService;
import com.example.JSON.CarDealer.Service.SupplierService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private SeedService seedService;
    private CustomerService customerService;
    private CarService carService;
    private SupplierService supplierService;

    private Gson gson;

    @Autowired
    public CommandLineRunnerImpl(SeedService seedService, CustomerService customerService, CarService carService, SupplierService supplierService) {
        this.seedService = seedService;
        this.customerService = customerService;
        this.carService = carService;
        this.supplierService = supplierService;

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                    @Override
                    public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
                        return new JsonPrimitive(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    }
                })
                .create();
    }

    @Override
    public void run(String... args) throws Exception {
        //this.seedService.seedAll();

//        Query 1 – Ordered Customers
//        selectAllCustomersOrderedBirthDay();

//        Query 2 – Cars from Make Toyota
//        selectMakeToyotaModels();

//        Query 3 – Local Suppliers
//        selectAllSuppliersThatNotImportFromAbroad();

        // TODO: This exercise is uncompleted
//        Query 4 – Cars with Their List of Parts
//        selectAllCarsWithTheirParts();
    }

    private void selectAllCarsWithTheirParts() {
        List<CarsWithTheirPartsDto> carsParts = this.carService.findAllCarsWithTheirParts();


        System.out.println(this.gson.toJson(carsParts));
    }

    private void selectAllSuppliersThatNotImportFromAbroad() {
        List<SuppliersNotPartsFromAbroadDto> suppliers = this.supplierService.findAllSuppliersThatNotImportPartsFromAbroad();

        System.out.println(this.gson.toJson(suppliers));
    }

    private void selectMakeToyotaModels() {
        List<CarToyotaModelsDto> cars = this.carService.findAllMakeToyotaModels();

        System.out.println(this.gson.toJson(cars));
    }

    private void selectAllCustomersOrderedBirthDay() {
        List<CustomersBirthDayDto> customersBirthDay = this.customerService.findAllCustomersAndTheirBirthDay();

        System.out.println(this.gson.toJson(customersBirthDay));
    }
}
