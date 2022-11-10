package com.example.JSON.ProductShop.DTO;

public class UserAndProductsSoldDto {
    private String firstName;
    private String lastName;
    private int age;
    private SellProductsDto sellProducts;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SellProductsDto getSellProducts() {
        return sellProducts;
    }

    public void setSellProducts(SellProductsDto sellProducts) {
        this.sellProducts = sellProducts;
    }

}
