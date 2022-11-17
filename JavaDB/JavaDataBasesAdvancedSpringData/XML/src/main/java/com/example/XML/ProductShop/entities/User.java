package com.example.XML.ProductShop.entities;

import com.example.XML.ProductShop.Exceptions.ValidationExceptionName;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private Integer age;

    @OneToMany(mappedBy = "seller", targetEntity = Product.class)
    private Set<Product> sellProducts;

    @OneToMany(mappedBy = "buyer", targetEntity = Product.class)
    private Set<Product> buyProducts;

    @ManyToMany
    private Set<User> friends;

    public User() {
        this.buyProducts = new LinkedHashSet<>();
        this.sellProducts = new LinkedHashSet<>();
        this.friends = new LinkedHashSet<>();
    }

    public User(String firstName, String lastName, Integer age) {
        this();
        this.firstName = firstName;
        this.setLastName(lastName);
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (lastName.length() < 3) {
            throw new ValidationExceptionName("Incorrect lastName");
        }

        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Product> getSellProducts() {
        return sellProducts;
    }

    public void setSellProducts(Set<Product> sellProducts) {
        this.sellProducts = sellProducts;
    }

    public Set<Product> getBuyProducts() {
        return buyProducts;
    }

    public void setBuyProducts(Set<Product> buyProducts) {
        this.buyProducts = buyProducts;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public String getFullName() {
        if (this.firstName == null) {
            return this.lastName;
        }

        return this.firstName + " " + this.lastName;
    }
}
