package com.example.JSON.CarDealer.Service;

import com.example.JSON.CarDealer.DTO.SuppliersNotPartsFromAbroadDto;

import java.util.List;

public interface SupplierService {

    List<SuppliersNotPartsFromAbroadDto> findAllSuppliersThatNotImportPartsFromAbroad();
}
