package com.example.XML.CarDealer.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPartsDto {

    @XmlElement(name = "part")
    private List<PartsDto> parts;

    public ImportPartsDto() {

    }

    public List<PartsDto> getParts() {
        return parts;
    }
}
