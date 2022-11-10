package com.example.JSON.CarDealer.DTO;

public class SuppliersNotPartsFromAbroadDto {
    private int id;
    private String name;
    private long partsCount;

    public SuppliersNotPartsFromAbroadDto(int id, String name, long partsCount) {
        this.id = id;
        this.name = name;
        this.partsCount = partsCount;
    }

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

    public long getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(long partsCount) {
        this.partsCount = partsCount;
    }
}
