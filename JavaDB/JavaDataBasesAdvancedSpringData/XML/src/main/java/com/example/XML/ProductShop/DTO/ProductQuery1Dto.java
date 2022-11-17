package com.example.XML.ProductShop.DTO;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductQuery1Dto {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private BigDecimal price;

    @XmlAttribute
    private String seller;

    public ProductQuery1Dto() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
