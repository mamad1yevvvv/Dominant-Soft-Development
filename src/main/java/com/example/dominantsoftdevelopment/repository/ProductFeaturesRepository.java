package com.example.dominantsoftdevelopment.repository;

import com.example.dominantsoftdevelopment.model.ProductFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductFeaturesRepository extends JpaRepository<ProductFeatures,Long> {
    List<ProductFeatures> findByProduct_IdAndDeletedFalse(Long id);
    List<ProductFeatures> findByProduct_Id(Long product_id);
}
