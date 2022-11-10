package com.example.JSON.ProductShop.Repository;

import com.example.JSON.ProductShop.DTO.CategoryDateDto;
import com.example.JSON.ProductShop.DTO.ProductRangeNotBuyerDto;
import com.example.JSON.ProductShop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT new com.example.JSON.ProductShop.DTO.ProductRangeNotBuyerDto(p.name, p.price, p.seller.firstName, p.seller.lastName)" +
            " FROM products AS p" +
            " WHERE p.price BETWEEN :fromPrice AND :toPrice AND p.buyer IS NULL" +
            " ORDER BY p.price ASC")
    List<ProductRangeNotBuyerDto> selectProductsRange(BigDecimal fromPrice, BigDecimal toPrice);

    @Query("SELECT new com.example.JSON.ProductShop.DTO.CategoryDateDto(c.name AS category, COUNT(p) AS productsCount, AVG(p.price) AS averagePrice, SUM(p.price) AS totalRevenue)" +
            " FROM products AS p" +
            " JOIN p.categories AS c" +
            " GROUP BY c.id")
    List<CategoryDateDto> findCategoryWithTheirProductsDate();
}
