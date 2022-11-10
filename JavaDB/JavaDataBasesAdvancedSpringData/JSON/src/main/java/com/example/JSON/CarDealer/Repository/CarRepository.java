package com.example.JSON.CarDealer.Repository;

import com.example.JSON.CarDealer.DTO.CarsWithTheirPartsDto;
import com.example.JSON.CarDealer.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query("SELECT c FROM Car AS c" +
            " WHERE c.make LIKE 'Toyota'" +
            " ORDER BY c.model ASC, c.travelledDistance DESC")
    List<Car> findAllToyotaModels();

    @Query("SELECT new com.example.JSON.CarDealer.DTO.CarsWithTheirPartsDto(c AS car, p AS parts)" +
            " FROM Car AS c" +
            " JOIN c.parts AS p")
    List<CarsWithTheirPartsDto> findCarsWithParts();


}
