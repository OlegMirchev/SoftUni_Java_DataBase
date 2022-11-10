package com.example.JSON.CarDealer.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class CustomersBirthDayDto {
    private int id;
    private String name;
    private LocalDateTime birthDate;
    private boolean isYoungDriver;
    private List<CustomerDto> sales;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }


    public List<CustomerDto> getSales() {
        return sales;
    }

    public void setSales(List<CustomerDto> sales) {
        this.sales = sales;
    }
}
