package com.example.XML.ProductShop.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeedCategoryDto {

    @XmlElement(name = "category")
    private List<CategoryDto> categories;

    public SeedCategoryDto() {

    }

    public List<CategoryDto> getCategories() {
        return categories;
    }
}
