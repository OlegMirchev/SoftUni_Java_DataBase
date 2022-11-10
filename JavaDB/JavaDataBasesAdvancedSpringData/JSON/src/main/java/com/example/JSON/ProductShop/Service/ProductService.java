package com.example.JSON.ProductShop.Service;

import com.example.JSON.ProductShop.DTO.CategoryDateDto;
import com.example.JSON.ProductShop.DTO.ProductRangeNotBuyerDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<ProductRangeNotBuyerDto> selectProductInRangeNotBuyer(BigDecimal fromPrice, BigDecimal toPrice);

    List<CategoryDateDto> findCategoryWithProductsDate();
}
