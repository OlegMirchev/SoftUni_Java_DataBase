package com.example.demo.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(length = 30, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(name = "registered_on")
    private LocalDateTime registeredOn;

    @Column(name = "last_time_logged_in")
    private LocalDateTime lastTimeLoggedIn;

    private int age;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne(optional = false)
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town town;

    @ManyToMany
    private Set<Friend> friends;

    public User() {

    }

    public User(String username, String password, String email, String firstName, String lastName, LocalDateTime registeredOn, LocalDateTime lastTimeLoggedIn, int age, boolean isDeleted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.registeredOn = registeredOn;
        this.lastTimeLoggedIn = lastTimeLoggedIn;
        this.age = age;
        this.isDeleted = isDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username.length() < 4 && username.trim().isEmpty()) {
            throw new IllegalArgumentException("The username is invalid");
        }

        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() < 4 && password.trim().isEmpty()) {
            throw new IllegalArgumentException("The password is invalid");
        }

        for (int i = 0; i < password.length(); i++) {
            if (!Character.isDigit(password.charAt(i))) {
                throw new IllegalArgumentException("The password not contain one digit at least");
            }

            if (!Character.isUpperCase(password.charAt(i))) {
                throw new IllegalArgumentException("The password not contain one upper letter at least");
            }

            if (!Character.isLowerCase(password.charAt(i))) {
                throw new IllegalArgumentException("The password not contain one lower letter at least");
            }

            if (!password.contains("(!, @, #, $, %, ^, &, *, (, ), _, +, <, >, ?)")) {
                throw new IllegalArgumentException("The password not contain one special symbol letter at least");
            }
        }

        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+[._a-zA-Z0-9!#$%&'*+-/=?^_`{|}~]*[a-zA-Z]*@[a-zA-Z0-9]{2,8}.[a-zA-Z.]{2,6}");
        Matcher matcher = pattern.matcher(email);

        if (!matcher.find() && email.trim().isEmpty()) {
            throw new IllegalArgumentException("The email is invalid");
        }

        this.email = email;
    }

    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }

    public LocalDateTime getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(LocalDateTime lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Set<Friend> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friend> friends) {
        this.friends = friends;
    }

    public String fullName() {
        return this.firstName + " " + this.lastName;
    }
}
