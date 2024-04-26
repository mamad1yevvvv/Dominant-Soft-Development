package com.example.dominantsoftdevelopment.dto;

import com.example.dominantsoftdevelopment.model.ProductFeatureName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class  ProductFeatureValueCreateAndUpdateDto {
    private Long productFeatureNameId;
    private String value;
}
