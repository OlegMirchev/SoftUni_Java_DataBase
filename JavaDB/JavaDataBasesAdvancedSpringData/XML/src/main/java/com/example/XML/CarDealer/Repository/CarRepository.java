package com.example.XML.CarDealer.Repository;

import com.example.XML.CarDealer.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query("SELECT c FROM Car AS c" +
            " WHERE c.make LIKE 'Toyota'" +
            " ORDER BY c.model ASC, c.travelledDistance DESC")
    List<Car> findAllCarsWithMakeToyota();
}
