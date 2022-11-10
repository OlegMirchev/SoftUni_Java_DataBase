package com.example.JSON.ProductShop.DTO;

import java.util.List;

public class SellProductsDto {
    private int count;
    private List<ProductsDto> sellProducts;

    public List<ProductsDto> getSellProducts() {
        return sellProducts;
    }

    public void setSellProducts(List<ProductsDto> sellProducts) {
        this.sellProducts = sellProducts;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
