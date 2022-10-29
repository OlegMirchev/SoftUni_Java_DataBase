package com.example.SpringDateMappingObj.DTO.User;

import com.example.SpringDateMappingObj.Exception.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterDto {
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    public RegisterDto(String email, String password, String confirmPassword, String fullName) throws ValidationException {
        this.setEmail(email);
        this.setPassword(password);
        this.setConfirmPassword(confirmPassword);
        this.setFullName(fullName);
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) throws ValidationException {
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

    private void setPassword(String password) {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    private void setConfirmPassword(String confirmPassword) {
        if (!this.password.equals(confirmPassword)) {
            throw new ValidationException("Incorrect username / password");
        }

        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    private void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
