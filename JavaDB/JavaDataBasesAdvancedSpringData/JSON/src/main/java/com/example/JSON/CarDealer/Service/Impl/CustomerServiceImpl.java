package com.example.JSON.CarDealer.Service.Impl;

import com.example.JSON.CarDealer.DTO.CustomersBirthDayDto;
import com.example.JSON.CarDealer.Repository.CustomerRepository;
import com.example.JSON.CarDealer.Service.CustomerService;
import com.example.JSON.CarDealer.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

        this.modelMapper = new ModelMapper();
    }

    @Override
    @Transactional
    public List<CustomersBirthDayDto> findAllCustomersAndTheirBirthDay() {
        List<Customer> customers = this.customerRepository.findCustomersAndTheirBirthDay();

        List<Customer> listCustomersExperiencedDrivers = experiencedDrivers(customers);

        return listCustomersExperiencedDrivers.stream().map(c -> this.modelMapper.map(c, CustomersBirthDayDto.class)).collect(Collectors.toList());
    }

    private List<Customer> experiencedDrivers(List<Customer> customers) {
        List<Customer> list = new ArrayList<>();
        for (int i = 0; i <= customers.size() - 1; i++) {
            if (i == customers.size() - 1) {
                list.add(customers.get(i));
                break;
            }

            if (customers.get(i).getBirthDate().equals(customers.get(i + 1).getBirthDate())) {
                if (!customers.get(i).isYoungDriver()) {
                    list.add(customers.get(i));
                }else if(!customers.get(i + 1).isYoungDriver()) {
                    list.add(customers.get(i + 1));
                }

                i++;
                continue;
            }

            list.add(customers.get(i));
        }

        return list;
    }
}
