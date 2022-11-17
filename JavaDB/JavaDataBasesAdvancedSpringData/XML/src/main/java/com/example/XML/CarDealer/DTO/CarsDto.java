package com.example.XML.CarDealer.DTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
public class CarsDto {

    @XmlElement
    private String make;

    @XmlElement
    private String model;

    @XmlElement(name = "travelled-distance")
    private long travelledDistance;

    public CarsDto() {

    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public long getTravelledDistance() {
        return travelledDistance;
    }
}
