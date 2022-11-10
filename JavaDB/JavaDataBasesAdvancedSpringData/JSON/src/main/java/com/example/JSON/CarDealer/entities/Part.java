package com.example.JSON.CarDealer.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "parts")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    private Supplier supplier;

//    @ManyToMany
//    @JoinTable(
//            name = "parts_cars",
//            joinColumns = @JoinColumn(name = "part_id"),
//            inverseJoinColumns = @JoinColumn(name = "car_id")
//    )
//    private Set<Car> cars;

    public Part() {
        //this.cars = new LinkedHashSet<>();
    }

    public Part(String name, BigDecimal price, int quantity) {
        this();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

//    public Set<Car> getCars() {
//        return cars;
//    }
//
//    public void setCars(Set<Car> cars) {
//        this.cars = cars;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return id == part.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
