package com.example.XML.CarDealer.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersOrdersByBirthDateDto {

    @XmlElement(name = "customer")
    private List<CustomerBirthDateDto> customers;

    public CustomersOrdersByBirthDateDto() {

    }

    public CustomersOrdersByBirthDateDto(List<CustomerBirthDateDto> customers) {
        this.customers = customers;
    }
}
