package com.example.XML.ProductShop.Service;


import com.example.XML.ProductShop.DTO.CategoriesByProductCountDto;
import com.example.XML.ProductShop.DTO.ProductsWithRangeNotBayerDto;

import java.math.BigDecimal;

public interface ProductService {

    ProductsWithRangeNotBayerDto findAllProductsWithRange(BigDecimal fromPrice, BigDecimal toPrice);

    CategoriesByProductCountDto findAllCategoriesByProductCount();
}
