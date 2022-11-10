package com.example.JSON.ProductShop.Service.Impl;

import com.example.JSON.ProductShop.DTO.SellProductsDto;
import com.example.JSON.ProductShop.DTO.UserAndProductsSoldDto;
import com.example.JSON.ProductShop.DTO.UserWithSoldProductsDto;
import com.example.JSON.ProductShop.Repository.UserRepository;
import com.example.JSON.ProductShop.Service.UserService;
import com.example.JSON.ProductShop.entities.Product;
import com.example.JSON.ProductShop.entities.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

        this.modelMapper = new ModelMapper();


    }

    @Override
    @Transactional
    public List<UserWithSoldProductsDto> findUserWithThemSoldProducts() {
        List<User> users = this.userRepository.findAllUsersAndTheirSoldProducts();

        return users.stream().map(u -> this.modelMapper.map(u, UserWithSoldProductsDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<UserAndProductsSoldDto> findAllUsersWithProductsSold() {
        List<User> allUsers = this.userRepository.findAllUsersAndProducts();

        Converter<User, Integer> converter = mappingContext -> mappingContext.getSource() == null ? null
                : mappingContext.getSource().getSellProducts().size();

        TypeMap<Product, SellProductsDto> typeMap = this.modelMapper.createTypeMap(Product.class, SellProductsDto.class);
        typeMap.addMappings(map -> map.using(converter).map(Product::getSellProducts, SellProductsDto::setCount));

        return allUsers.stream().map(u -> this.modelMapper.map(u, UserAndProductsSoldDto.class)).collect(Collectors.toList());
    }
}
