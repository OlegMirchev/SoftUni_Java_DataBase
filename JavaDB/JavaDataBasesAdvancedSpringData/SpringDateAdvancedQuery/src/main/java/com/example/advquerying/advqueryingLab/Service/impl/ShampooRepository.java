package com.example.advquerying.advqueryingLab.Service.impl;

import com.example.advquerying.advqueryingLab.entities.Shampoo;
import com.example.advquerying.advqueryingLab.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    @Query("SELECT s FROM Shampoo AS s WHERE s.size LIKE :size ORDER BY s.id ASC")
    List<Shampoo> findShampooBySizeAndOrderById(Size size);

    @Query("SELECT s FROM Shampoo AS s" +
            " JOIN s.label AS l" +
            " WHERE s.size LIKE :size OR l.id = :labelId" +
            " ORDER BY s.price ASC")
    List<Shampoo> findShampooWithGivenSizeOrLabelId(Size size, Long labelId);

    @Query("SELECT s FROM Shampoo AS s" +
            " WHERE s.price > :number" +
            " ORDER BY s.price DESC")
    List<Shampoo> findShampooGreaterThanOrderByPriceDesc(@Param("number") BigDecimal price);

    int countByPriceLessThan(BigDecimal bigDecimal);

    @Query("SELECT s FROM Shampoo AS s" +
            " JOIN s.ingredients AS i" +
            " WHERE i.name IN :ingredients" +
            " GROUP BY s.brand")
    List<Shampoo> findAllShampooIncludedThatIngredients(List<String> ingredients);

    @Query("SELECT s.brand FROM Shampoo AS s" +
            " JOIN s.ingredients AS i" +
            " WHERE s.ingredients.size < :digit")
    List<String> findAllShampoosWhichCountIngredientsLessThan(@Param("digit") int number);
}
