package com.example.SpringDateMappingObj.Service.Impl;

import com.example.SpringDateMappingObj.DTO.User.LoginDto;
import com.example.SpringDateMappingObj.DTO.User.RegisterDto;
import com.example.SpringDateMappingObj.Exception.UserNotLoggedException;
import com.example.SpringDateMappingObj.Repository.UserRepository;
import com.example.SpringDateMappingObj.Service.UserService;
import com.example.SpringDateMappingObj.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private User currentUser;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.currentUser = null;
        this.userRepository = userRepository;
    }

    @Override
    public User register(RegisterDto registerDto) {
        if (this.currentUser != null) {
            throw new IllegalArgumentException("User is logged");
        }

        ModelMapper modelMapper = new ModelMapper();
        User mapUser = modelMapper.map(registerDto, User.class);

        long count = this.userRepository.count();

        if (count == 0) {
            mapUser.setAdministrator(true);
        }

        return this.userRepository.save(mapUser);
    }

    @Override
    public Optional<User> login(LoginDto loginDto) {
        Optional<User> byEmailAndPassword = this.userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());

        byEmailAndPassword.ifPresent(v -> this.currentUser = v);

        return byEmailAndPassword;
    }

    @Override
    public void logout() {
        this.currentUser = null;
    }

    public User getLoggedUser() {
        if (this.currentUser == null) {
            throw new UserNotLoggedException("Cannot log out. No user was logged in.");
        }

        return this.currentUser;
    }
}
