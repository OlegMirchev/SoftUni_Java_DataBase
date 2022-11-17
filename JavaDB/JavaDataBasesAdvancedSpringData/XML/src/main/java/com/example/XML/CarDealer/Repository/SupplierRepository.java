package com.example.XML.CarDealer.Repository;

import com.example.XML.CarDealer.DTO.SupplierNotSupplyAbroadDto;
import com.example.XML.CarDealer.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    @Query("SELECT new com.example.XML.CarDealer.DTO.SupplierNotSupplyAbroadDto(s.id, s.name, COUNT(p) AS partsCount)" +
            " FROM Supplier AS s" +
            " JOIN s.parts AS p" +
            " WHERE s.isImporter = 0" +
            " GROUP BY s.id")
    List<SupplierNotSupplyAbroadDto> findAllSuppliersWhichNotThereSupplyAbroad();
}
