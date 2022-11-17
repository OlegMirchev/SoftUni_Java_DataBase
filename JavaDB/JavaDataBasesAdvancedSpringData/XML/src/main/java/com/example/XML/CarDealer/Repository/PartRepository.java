package com.example.XML.CarDealer.Repository;

import com.example.XML.CarDealer.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer> {

}
