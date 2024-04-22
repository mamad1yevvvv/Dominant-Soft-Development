package com.example.dominantsoftdevelopment.repository;

import com.example.dominantsoftdevelopment.model.Discount;
import com.example.dominantsoftdevelopment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long>, JpaSpecificationExecutor<Discount> {

    @Query("SELECT d FROM Discount d WHERE d.productId.id = :productId ORDER BY d.createdAt DESC LIMIT 1")
    Optional<Discount> findLastDiscountByProductId(Long productId);

    List<Discount> findAllByProductIdAndIsActiveTrue(Product product);
}
