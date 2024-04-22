package com.example.dominantsoftdevelopment.repository;

import com.example.dominantsoftdevelopment.model.ProductFeatureName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFeaturesNameRepository extends JpaRepository<ProductFeatureName,Long> {
}
