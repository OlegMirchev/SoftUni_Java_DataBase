package com.example.XML.CarDealer.DTO;

import com.example.XML.CarDealer.utils.LocalDateTimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersDto {

    @XmlAttribute
    private String name;

    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime birthDate;

    @XmlElement(name = "is-young-driver")
    private boolean isYoungDriver;

    public CustomersDto() {

    }

    public String getName() {
        return name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }
}
