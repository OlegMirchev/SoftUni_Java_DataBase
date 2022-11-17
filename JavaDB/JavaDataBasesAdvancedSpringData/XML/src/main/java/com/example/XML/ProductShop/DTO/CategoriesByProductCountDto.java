package com.example.XML.ProductShop.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesByProductCountDto {

    @XmlElement(name = "category")
    private List<CategoryDto> categories;

    public CategoriesByProductCountDto() {

    }

    public CategoriesByProductCountDto(List<CategoryDto> categories) {
        this.categories = categories;
    }
}
