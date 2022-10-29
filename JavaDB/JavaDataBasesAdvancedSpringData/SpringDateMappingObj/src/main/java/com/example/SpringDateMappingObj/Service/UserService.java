package com.example.SpringDateMappingObj.Service;

import com.example.SpringDateMappingObj.DTO.User.LoginDto;
import com.example.SpringDateMappingObj.DTO.User.RegisterDto;
import com.example.SpringDateMappingObj.entities.User;

import java.util.Optional;

public interface UserService {

    User register(RegisterDto registerDto);

    Optional<User> login(LoginDto loginDto);

    User getLoggedUser();

    void logout();
}
