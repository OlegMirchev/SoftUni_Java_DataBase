package com.example.XML.CarDealer.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsWithMakeToyotaDto {

    @XmlElement(name = "car")
    private List<CarToyotaDto> cars;

    public CarsWithMakeToyotaDto() {

    }

    public CarsWithMakeToyotaDto(List<CarToyotaDto> cars) {
        this.cars = cars;
    }
}
