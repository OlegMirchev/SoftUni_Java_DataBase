package com.example.XML.CarDealer.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierDto {

    @XmlAttribute
    private String name;

    @XmlAttribute(name = "is-importer")
    private boolean isImporter;

    public SupplierDto() {

    }

    public String getName() {
        return name;
    }

    public boolean isImporter() {
        return isImporter;
    }
}
