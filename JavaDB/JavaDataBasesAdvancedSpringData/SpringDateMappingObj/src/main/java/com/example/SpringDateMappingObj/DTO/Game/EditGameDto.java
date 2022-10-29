package com.example.SpringDateMappingObj.DTO.Game;

import com.example.SpringDateMappingObj.Exception.ValidationException;

import java.math.BigDecimal;

public class EditGameDto {
    private int id;
    private BigDecimal price;
    private float size;

    public EditGameDto(int id, BigDecimal price, float size) {
        this.id = id;
        this.setPrice(price);
        this.setSize(size);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    private void setPrice(BigDecimal price) {
        if (price.intValue() < 0) {
            throw new ValidationException("Negative price");
        }

        this.price = price;
    }

    public float getSize() {
        return size;
    }

    private void setSize(float size) {
        if (size < 0) {
            throw new ValidationException("Negative size");
        }

        this.size = size;
    }
}
