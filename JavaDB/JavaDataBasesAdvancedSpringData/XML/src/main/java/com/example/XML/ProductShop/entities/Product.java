package com.example.XML.ProductShop.entities;

import com.example.XML.ProductShop.Exceptions.ValidationExceptionName;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private BigDecimal price;

    @ManyToOne
    private User buyer;

    @ManyToOne
    private User seller;

    @ManyToMany
    private Set<Category> categories;

    public Product() {
        this.categories = new LinkedHashSet<>();
    }

    public Product(String name, BigDecimal price) {
        this();
        this.setName(name);
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() < 3) {
            throw new ValidationExceptionName("Incorrect name");
        }

        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public int getSellProducts() {
        return this.seller.getSellProducts().size();
    }
}
