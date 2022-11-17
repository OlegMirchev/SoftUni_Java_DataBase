package com.example.XML.ProductShop.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithBuyerAndOneSoldProductDto {

    @XmlElement(name = "user")
    private List<UsersSoldProductDto> users;

    public UsersWithBuyerAndOneSoldProductDto() {

    }

    public UsersWithBuyerAndOneSoldProductDto(List<UsersSoldProductDto> users) {
        this.users = users;
    }
}
