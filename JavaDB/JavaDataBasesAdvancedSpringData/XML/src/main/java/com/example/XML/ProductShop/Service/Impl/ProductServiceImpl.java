package com.example.XML.ProductShop.Service.Impl;


import com.example.XML.ProductShop.DTO.CategoriesByProductCountDto;
import com.example.XML.ProductShop.DTO.CategoryDto;
import com.example.XML.ProductShop.DTO.ProductQuery1Dto;
import com.example.XML.ProductShop.DTO.ProductsWithRangeNotBayerDto;
import com.example.XML.ProductShop.Repository.ProductRepository;
import com.example.XML.ProductShop.Service.ProductService;
import com.example.XML.ProductShop.entities.Product;
import com.example.XML.ProductShop.entities.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private TypeMap<Product, ProductQuery1Dto> typeConvertNameUser;

    private ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;

        this.modelMapper = new ModelMapper();

        Converter<User, String> converterUserName = u -> u.getSource() == null ? null : u.getSource().getFullName();

        TypeMap<Product, ProductQuery1Dto> typeMap = this.modelMapper.createTypeMap(Product.class, ProductQuery1Dto.class);

        this.typeConvertNameUser = typeMap.addMappings(map -> map.using(converterUserName).map(Product::getSeller, ProductQuery1Dto::setSeller));

        this.modelMapper.addConverter(converterUserName);
    }

    @Override
    public ProductsWithRangeNotBayerDto findAllProductsWithRange(BigDecimal fromPrice, BigDecimal toPrice) {
        List<Product> products = this.productRepository.findAllByPriceBetweenOrderByPriceDesc(fromPrice, toPrice);

        List<ProductQuery1Dto> listProducts = products.stream().map(p -> this.typeConvertNameUser.map(p)).collect(Collectors.toList());

        return new ProductsWithRangeNotBayerDto(listProducts);
    }

    @Override
    public CategoriesByProductCountDto findAllCategoriesByProductCount() {
        List<CategoryDto> categories = this.productRepository.findCategoryWithTheirProductsDate();

        return new CategoriesByProductCountDto(categories);
    }
}
