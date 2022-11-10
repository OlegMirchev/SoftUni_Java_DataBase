package com.example.JSON.CarDealer.Repository;

import com.example.JSON.CarDealer.DTO.SuppliersNotPartsFromAbroadDto;
import com.example.JSON.CarDealer.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    @Query("SELECT new com.example.JSON.CarDealer.DTO.SuppliersNotPartsFromAbroadDto(s.id, s.name, COUNT(p))" +
            " FROM Supplier AS s" +
            " JOIN s.parts AS p" +
            " WHERE s.isImporter = 0" +
            " GROUP BY s.id")
    List<SuppliersNotPartsFromAbroadDto> findSuppliersThatNotImportPartsFromAbroad();
}
