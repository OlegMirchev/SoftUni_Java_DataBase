package com.example.XML.CarDealer.Repository;

import com.example.XML.CarDealer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer AS c" +
            " ORDER BY c.birthDate ASC")
    List<Customer> findAllCustomersOrderByTheirBirthDate();
}
