package com.example.XML.CarDealer.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportSuppliersDto {

    @XmlElement(name = "supplier")
    private List<SupplierDto> suppliers;

    public ImportSuppliersDto() {

    }

    public List<SupplierDto> getSuppliers() {
        return suppliers;
    }
}
