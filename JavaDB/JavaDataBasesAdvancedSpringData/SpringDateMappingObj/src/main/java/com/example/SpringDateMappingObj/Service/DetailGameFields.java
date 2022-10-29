package com.example.SpringDateMappingObj.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface DetailGameFields {
    String getTitle();
    BigDecimal getPrice();
    String getDescription();
    LocalDate getReleaseDate();
}
