package com.example.XML.ProductShop.Service.Impl;

import com.example.XML.ProductShop.DTO.UsersSoldProductDto;
import com.example.XML.ProductShop.DTO.UsersWithBuyerAndOneSoldProductDto;
import com.example.XML.ProductShop.Repository.UserRepository;
import com.example.XML.ProductShop.Service.UserService;
import com.example.XML.ProductShop.entities.User;
import org.modelmapper.ModelMapper;
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
    public UsersWithBuyerAndOneSoldProductDto selectAllUsersWithBuyerAndSoldProduct() {
        List<User> users = this.userRepository.findAllUsersAndTheirSoldProducts();

        List<UsersSoldProductDto> listUsers = users.stream().map(u -> this.modelMapper.map(u, UsersSoldProductDto.class)).collect(Collectors.toList());

        return new UsersWithBuyerAndOneSoldProductDto(listUsers);
    }
}
