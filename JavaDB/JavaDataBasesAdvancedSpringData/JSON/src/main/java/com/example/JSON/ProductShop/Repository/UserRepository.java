package com.example.JSON.ProductShop.Repository;

import com.example.JSON.ProductShop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM users AS u" +
            " JOIN u.sellProducts AS p" +
            " WHERE p.buyer IS NOT NULL")
    List<User> findAllUsersAndTheirSoldProducts();

    @Query("SELECT DISTINCT u" +
            " FROM users AS u" +
            " JOIN u.sellProducts AS p" +
            " WHERE p.seller IS NOT NULL" +
            " ORDER BY size(u.sellProducts) DESC, u.lastName ASC")
    List<User> findAllUsersAndProducts();
}
