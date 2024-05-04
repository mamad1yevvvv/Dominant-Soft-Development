package com.example.dominantsoftdevelopment.repository;

import com.example.dominantsoftdevelopment.model.Favourite;
import com.example.dominantsoftdevelopment.model.Product;
import com.example.dominantsoftdevelopment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    @Query("SELECT f.product FROM Favourite f WHERE f.user = :user")
    List<Product> findProductsByUser(User user);

    @Transactional
    void deleteByUserAndProduct(User user, Product product);
}
