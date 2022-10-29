package com.example.SpringDateMappingObj.DTO.Game;

import com.example.SpringDateMappingObj.Exception.ValidationException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddGameDto {
    private String title;
    private BigDecimal price;
    private float size;
    private String trailer;
    private String imageThumbnail;
    private String description;
    private LocalDate releaseDate;

    public AddGameDto(String title, BigDecimal price, float size, String trailer, String imageThumbnail, String description, LocalDate releaseDate) {
        this.setTitle(title);
        this.setPrice(price);
        this.setSize(size);
        this.setTrailer(trailer);
        this.setImageThumbnail(imageThumbnail);
        this.setDescription(description);
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        if (!Character.isUpperCase(title.charAt(0)) || title.length() < 3 || title.length() > 100) {
            throw new ValidationException("Incorrect title");
        }

        this.title = title;
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

    public String getTrailer() {
        return trailer;
    }

    private void setTrailer(String trailer) {
        //String substringId = trailer.substring(32);
        //!trailer.contains("https://www.youtube.com/") ||

        if (trailer.length() != 11) {
            throw new ValidationException("Incorrect trailer");
        }

        this.trailer = trailer;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    private void setImageThumbnail(String imageThumbnail) {
        if (!imageThumbnail.startsWith("http://") && !imageThumbnail.startsWith("https://")) {
            throw new ValidationException("Incorrect imageThumbnail");
        }

        this.imageThumbnail = imageThumbnail;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        if (description.length() < 20) {
            throw new ValidationException("Incorrect description");
        }

        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
