package com.example.XML.ProductShop.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeedUserDto {

    @XmlElement(name = "user")
    private List<UserDto> users;

    public SeedUserDto() {

    }

    public List<UserDto> getUsers() {
        return users;
    }
}
