package com.example.advquerying.advqueryingLab.Service.impl;

import com.example.advquerying.advqueryingLab.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByNameStartingWith(String letter);

    List<Ingredient> findByNameInOrderByPriceAsc(List<String> ingredients);

    @Modifying
    @Transactional
    @Query("DELETE FROM Ingredient AS i" +
            " WHERE i.name LIKE :name")
    int deleteIngredientsByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE FROM Ingredient AS i" +
            " SET i.price = i.price + 10")
    int updateAllIngredientsByPrice();

    @Modifying
    @Transactional
    @Query("UPDATE FROM Ingredient AS i" +
            " SET i.price = i.price * 2" +
            " WHERE i.name IN :names")
    int updateAllIngredientsByName(@Param("names") List<String> ingredients);
}
