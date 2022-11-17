package com.example.XML.CarDealer.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface SeedService {

    void seedCars() throws FileNotFoundException, JAXBException;

    void seedCustomers() throws FileNotFoundException, JAXBException;

    void seedParts() throws FileNotFoundException, JAXBException;

    void seedSuppliers() throws FileNotFoundException, JAXBException;

    void seedSale();

    default void seedAll() throws FileNotFoundException, JAXBException {
        seedSuppliers();
        seedParts();
        seedCars();
        seedCustomers();
        seedSale();
    }
}
