package com.example.XML.CarDealer.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersNotThereNotThereSupplyAbroadDto {

    @XmlElement(name = "supplier")
    private List<SupplierNotSupplyAbroadDto> suppliers;

    public SuppliersNotThereNotThereSupplyAbroadDto() {

    }

    public SuppliersNotThereNotThereSupplyAbroadDto(List<SupplierNotSupplyAbroadDto> suppliers) {
        this.suppliers = suppliers;
    }
}
