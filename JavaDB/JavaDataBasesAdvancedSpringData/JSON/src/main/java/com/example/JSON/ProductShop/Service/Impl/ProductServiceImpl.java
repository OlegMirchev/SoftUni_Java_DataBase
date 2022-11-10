package com.example.JSON.ProductShop.Service.Impl;

import com.example.JSON.ProductShop.DTO.CategoryDateDto;
import com.example.JSON.ProductShop.DTO.ProductRangeNotBuyerDto;
import com.example.JSON.ProductShop.Repository.ProductRepository;
import com.example.JSON.ProductShop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductRangeNotBuyerDto> selectProductInRangeNotBuyer(BigDecimal fromPrice, BigDecimal toPrice) {
        return this.productRepository.selectProductsRange(fromPrice, toPrice);
    }

    @Override
    public List<CategoryDateDto> findCategoryWithProductsDate() {
        return this.productRepository.findCategoryWithTheirProductsDate();
    }
}
