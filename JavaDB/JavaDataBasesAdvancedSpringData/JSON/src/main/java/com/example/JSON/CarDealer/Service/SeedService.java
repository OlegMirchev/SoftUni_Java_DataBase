package com.example.JSON.CarDealer.Service;

import java.io.FileNotFoundException;

public interface SeedService {

    void seedCars() throws FileNotFoundException;

    void seedCustomers() throws FileNotFoundException;

    void seedParts() throws FileNotFoundException;

    void seedSuppliers() throws FileNotFoundException;

    void seedSale();

    default void seedAll() throws FileNotFoundException {
        seedSuppliers();
        seedParts();
        seedCars();
        seedCustomers();
        seedSale();
    }
}
