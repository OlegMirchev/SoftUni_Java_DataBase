package com.example.XML.CarDealer.Service.Impl;

import com.example.XML.CarDealer.DTO.CustomerBirthDateDto;
import com.example.XML.CarDealer.DTO.CustomersOrdersByBirthDateDto;
import com.example.XML.CarDealer.Repository.CustomerRepository;
import com.example.XML.CarDealer.Service.CustomerService;
import com.example.XML.CarDealer.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public CustomersOrdersByBirthDateDto selectAllCustomersOrderByBirthDate() {
        List<Customer> customers = this.customerRepository.findAllCustomersOrderByTheirBirthDate();

        List<Customer> experiencedDrivers = experiencedDrivers(customers);

        List<CustomerBirthDateDto> exportCustomers = experiencedDrivers.stream().map(ed -> this.modelMapper.map(ed, CustomerBirthDateDto.class)).collect(Collectors.toList());

        return new CustomersOrdersByBirthDateDto(exportCustomers);
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
