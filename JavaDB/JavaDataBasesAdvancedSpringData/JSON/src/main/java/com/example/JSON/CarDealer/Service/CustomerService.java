package com.example.JSON.CarDealer.Service;

import com.example.JSON.CarDealer.DTO.CustomersBirthDayDto;

import java.util.List;

public interface CustomerService {

    List<CustomersBirthDayDto> findAllCustomersAndTheirBirthDay();
}
