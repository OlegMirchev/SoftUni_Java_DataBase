package com.example.JSON.CarDealer.Service.Impl;

import com.example.JSON.CarDealer.DTO.CarToyotaModelsDto;
import com.example.JSON.CarDealer.DTO.CarsDto;
import com.example.JSON.CarDealer.DTO.CarsWithTheirPartsDto;
import com.example.JSON.CarDealer.DTO.PartsDto;
import com.example.JSON.CarDealer.Repository.CarRepository;
import com.example.JSON.CarDealer.Service.CarService;
import com.example.JSON.CarDealer.entities.Car;
import com.example.JSON.CarDealer.entities.Part;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;

        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<CarToyotaModelsDto> findAllMakeToyotaModels() {
        List<Car> allToyotaModels = this.carRepository.findAllToyotaModels();

        return allToyotaModels.stream().map(c -> this.modelMapper.map(c, CarToyotaModelsDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<CarsWithTheirPartsDto> findAllCarsWithTheirParts() {
        return this.carRepository.findCarsWithParts();
    }
}
