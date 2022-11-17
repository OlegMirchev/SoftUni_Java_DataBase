package com.example.XML.CarDealer.DTO;

import java.util.List;

public class CarsWithTheirPartsDto {
    private List<CarsDto> car;
    private List<PartsDto> parts;

    public CarsWithTheirPartsDto(List<CarsDto> car, List<PartsDto> parts) {
        this.car = car;
        this.parts = parts;
    }

    public List<CarsDto> getCar() {
        return car;
    }

    public void setCar(List<CarsDto> car) {
        this.car = car;
    }

    public List<PartsDto> getParts() {
        return parts;
    }

    public void setParts(List<PartsDto> parts) {
        this.parts = parts;
    }
}
