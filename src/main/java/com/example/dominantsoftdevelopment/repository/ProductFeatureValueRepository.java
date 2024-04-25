package com.example.dominantsoftdevelopment.repository;

import com.example.dominantsoftdevelopment.model.ProductFeatureValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProductFeatureValueRepository extends JpaRepository<ProductFeatureValue,Long> {

    List<ProductFeatureValue> findByProductFeatureName_Id(Long productFeatureName_id);
}
