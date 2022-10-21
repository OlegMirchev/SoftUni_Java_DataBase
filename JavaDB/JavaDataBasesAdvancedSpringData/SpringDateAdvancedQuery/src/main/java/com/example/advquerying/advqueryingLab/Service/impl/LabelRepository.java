package com.example.advquerying.advqueryingLab.Service.impl;

import com.example.advquerying.advqueryingLab.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {

}
