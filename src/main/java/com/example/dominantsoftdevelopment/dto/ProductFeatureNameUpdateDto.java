package com.example.dominantsoftdevelopment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductFeatureNameUpdateDto {

    private String name;
    private String measure;
    private Long categoryId;
}
