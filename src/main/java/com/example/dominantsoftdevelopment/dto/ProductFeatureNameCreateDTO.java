package com.example.dominantsoftdevelopment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductFeatureNameCreateDTO {
    String name;
    String measure;
    Long categoryId;
}
