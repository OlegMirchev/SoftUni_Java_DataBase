package com.example.XML.CarDealer.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDateTime birthDate;

    @Column(name = "is_young_driver", nullable = false)
    private boolean isYoungDriver;

    @OneToMany(mappedBy = "customer", targetEntity = Sale.class)
    private Set<Sale> sales;

    public Customer() {
        this.sales = new LinkedHashSet<>();
    }

    public Customer(String name, LocalDateTime birthDate, boolean isYoungDriver) {
        this();
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
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
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        this.isYoungDriver = youngDriver;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
