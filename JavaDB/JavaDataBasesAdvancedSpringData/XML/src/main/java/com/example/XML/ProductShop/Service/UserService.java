package com.example.XML.ProductShop.Service;

import com.example.XML.ProductShop.DTO.UsersWithBuyerAndOneSoldProductDto;

public interface UserService {
    UsersWithBuyerAndOneSoldProductDto selectAllUsersWithBuyerAndSoldProduct();
}
