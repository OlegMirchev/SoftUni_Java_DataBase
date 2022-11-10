package com.example.JSON.ProductShop.DTO;

import java.util.List;

public class UserWithSoldProductsDto {
    private String firstName;
    private String lastName;
    private List<SoldProductsDto> sellProducts;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public List<SoldProductsDto> getSellProducts() {
        return sellProducts;
    }

    public void setSellProducts(List<SoldProductsDto> sellProducts) {
        this.sellProducts = sellProducts;
    }
}
