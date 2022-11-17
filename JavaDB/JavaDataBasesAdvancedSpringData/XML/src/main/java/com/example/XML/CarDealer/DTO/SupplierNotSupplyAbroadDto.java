package com.example.XML.CarDealer.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierNotSupplyAbroadDto {

    @XmlAttribute
    private int id;

    @XmlAttribute
    private String name;

    @XmlAttribute(name = "parts-count")
    private long partsCount;

    public SupplierNotSupplyAbroadDto() {

    }

    public SupplierNotSupplyAbroadDto(int id, String name, long partsCount) {
        this.id = id;
        this.name = name;
        this.partsCount = partsCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPartsCount(long partsCount) {
        this.partsCount = partsCount;
    }
}
