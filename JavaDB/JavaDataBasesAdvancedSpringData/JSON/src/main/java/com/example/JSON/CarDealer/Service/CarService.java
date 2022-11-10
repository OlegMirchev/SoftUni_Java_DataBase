package com.example.JSON.CarDealer.Service;

import com.example.JSON.CarDealer.DTO.CarToyotaModelsDto;
import com.example.JSON.CarDealer.DTO.CarsWithTheirPartsDto;

import java.util.List;

public interface CarService {

    List<CarToyotaModelsDto> findAllMakeToyotaModels();

    List<CarsWithTheirPartsDto> findAllCarsWithTheirParts();

}
