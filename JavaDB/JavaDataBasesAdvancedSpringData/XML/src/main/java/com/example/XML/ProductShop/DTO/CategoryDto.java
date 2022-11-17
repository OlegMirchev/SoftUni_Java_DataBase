package com.example.XML.ProductShop.DTO;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryDto {

    @XmlAttribute
    private String name;

    @XmlElement(name = "products-count")
    private long productsCount;

    @XmlElement(name = "average-price")
    private double averagePrice;

    @XmlElement(name = "total-revenue")
    private BigDecimal totalRevenue;

    public CategoryDto() {

    }

    public CategoryDto(String name, long productsCount, double averagePrice, BigDecimal totalRevenue) {
        this.name = name;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductsCount(long productsCount) {
        this.productsCount = productsCount;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
