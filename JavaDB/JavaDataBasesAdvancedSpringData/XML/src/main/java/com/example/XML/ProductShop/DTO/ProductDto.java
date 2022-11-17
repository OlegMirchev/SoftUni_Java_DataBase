package com.example.XML.ProductShop.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDto {

    @XmlElement
    private String name;

    @XmlElement
    private BigDecimal price;

    public ProductDto() {

    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
