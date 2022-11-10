package com.example.JSON.CarDealer.Service.Impl;

import com.example.JSON.CarDealer.DTO.SuppliersNotPartsFromAbroadDto;
import com.example.JSON.CarDealer.Repository.SupplierRepository;
import com.example.JSON.CarDealer.Service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;

        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<SuppliersNotPartsFromAbroadDto> findAllSuppliersThatNotImportPartsFromAbroad() {
        return this.supplierRepository.findSuppliersThatNotImportPartsFromAbroad();
    }
}
