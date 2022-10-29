package com.example.SpringDateMappingObj.DTO.User;

import com.example.SpringDateMappingObj.Exception.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginDto {
    private String email;
    private String password;

    public LoginDto(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Pattern pattern = Pattern.compile("[a-z0-9]+@[a-z0-9]+\\.[a-z]+");
        Matcher matcher = pattern.matcher(email);

        if (!matcher.find()) {
            throw new ValidationException("Incorrect email.");
        }

        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        boolean isValidDigit = false;
        boolean isValidLower = false;
        boolean isValidUpper = false;

        for (int i = 0; i < password.length(); i++) {
            char letter = password.charAt(i);
            if (Character.isDigit(letter)) {
                isValidDigit = true;
            }
        }

        for (int i = 0; i < password.length(); i++) {
            char letter = password.charAt(i);
            if (Character.isLowerCase(letter)) {
                isValidLower = true;
            }
        }

        for (int i = 0; i < password.length(); i++) {
            char letter = password.charAt(i);
            if (Character.isUpperCase(letter)) {
                isValidUpper = true;
            }
        }

        if (!isValidDigit || !isValidLower || !isValidUpper || password.length() < 6) {
            throw new ValidationException("Incorrect username / password");
        }

        this.password = password;
    }
}
