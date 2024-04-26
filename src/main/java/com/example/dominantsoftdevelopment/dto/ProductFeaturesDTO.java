package com.example.dominantsoftdevelopment.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductFeaturesDTO {
    @Builder.Default
    Long id = null;
    ProductFeatureNameDTO productFeatureName;
    ProductFeatureValueDTO productFutureValue;
    String value;
}
