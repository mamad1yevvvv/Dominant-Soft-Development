package com.example.dominantsoftdevelopment.repository;

import com.example.dominantsoftdevelopment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByDeletedFalse();
}
