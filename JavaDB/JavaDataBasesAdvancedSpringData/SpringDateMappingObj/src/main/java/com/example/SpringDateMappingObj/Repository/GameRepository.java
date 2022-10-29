package com.example.SpringDateMappingObj.Repository;

import com.example.SpringDateMappingObj.Service.DetailGameFields;
import com.example.SpringDateMappingObj.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE games AS g" +
            " SET g.price = :price, g.size = :size" +
            " WHERE g.id = :id")
    int updatePriceAndSize(int id, BigDecimal price, float size);

    int deleteGameById(int id);

    @Query("SELECT g FROM games AS g")
    List<Game> selectTitleAndPrice();

    @Query("SELECT g.title AS title, g.price AS price, g.description AS description, g.releaseDate AS releaseDate FROM games AS g" +
            " WHERE g.title LIKE :name")
    List<DetailGameFields> selectDetailsAtGameIsThatTitle(@Param("name") String title);
}
