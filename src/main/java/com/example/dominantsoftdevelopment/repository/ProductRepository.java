package com.example.dominantsoftdevelopment.repository;

import com.example.dominantsoftdevelopment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
