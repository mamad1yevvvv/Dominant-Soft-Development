package com.example.dominantsoftdevelopment.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductFuturesDTO {
    ProductFeatureNameDTO productFeatureName;
    ProductFeatureValueDTO productFutureValue;
    String value;
}
