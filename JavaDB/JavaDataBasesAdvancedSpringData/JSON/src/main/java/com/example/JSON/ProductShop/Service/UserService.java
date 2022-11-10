package com.example.JSON.ProductShop.Service;

import com.example.JSON.ProductShop.DTO.UserAndProductsSoldDto;
import com.example.JSON.ProductShop.DTO.UserWithSoldProductsDto;

import java.util.List;

public interface UserService {
    List<UserWithSoldProductsDto> findUserWithThemSoldProducts();

    List<UserAndProductsSoldDto> findAllUsersWithProductsSold();
}
