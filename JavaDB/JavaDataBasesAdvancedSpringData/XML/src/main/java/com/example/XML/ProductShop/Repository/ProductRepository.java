package com.example.XML.ProductShop.Repository;


import com.example.XML.ProductShop.DTO.CategoryDto;
import com.example.XML.ProductShop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByPriceBetweenOrderByPriceDesc(BigDecimal fromPrice, BigDecimal toPrice);

    @Query("SELECT new com.example.XML.ProductShop.DTO.CategoryDto(c.name AS category, COUNT(p) AS productsCount, AVG(p.price) AS averagePrice, SUM(p.price) AS totalRevenue)" +
            " FROM products AS p" +
            " JOIN p.categories AS c" +
            " GROUP BY c.id")
    List<CategoryDto> findCategoryWithTheirProductsDate();
}
