package com.example.XML.CarDealer.Service.Impl;

import com.example.XML.CarDealer.DTO.SupplierNotSupplyAbroadDto;
import com.example.XML.CarDealer.DTO.SuppliersNotThereNotThereSupplyAbroadDto;
import com.example.XML.CarDealer.Repository.SupplierRepository;
import com.example.XML.CarDealer.Service.SupplierService;
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
    public SuppliersNotThereNotThereSupplyAbroadDto selectAllSuppliersWhichNotThereSupplyAbroad() {
        List<SupplierNotSupplyAbroadDto> exportSuppliers = this.supplierRepository.findAllSuppliersWhichNotThereSupplyAbroad();

        return new SuppliersNotThereNotThereSupplyAbroadDto(exportSuppliers);
    }
}
