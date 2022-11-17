package com.example.XML.CarDealer.Service.Impl;

import com.example.XML.CarDealer.DTO.CarToyotaDto;
import com.example.XML.CarDealer.DTO.CarsWithMakeToyotaDto;
import com.example.XML.CarDealer.Repository.CarRepository;
import com.example.XML.CarDealer.Service.CarService;
import com.example.XML.CarDealer.entities.Car;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public CarsWithMakeToyotaDto selectAllCarsWithMakeToyota() {
        List<Car> cars = this.carRepository.findAllCarsWithMakeToyota();

        List<CarToyotaDto> exportCars = cars.stream().map(c -> this.modelMapper.map(c, CarToyotaDto.class)).collect(Collectors.toList());

        return new CarsWithMakeToyotaDto(exportCars);

    }
}
