package com.example.dominantsoftdevelopment.model;

import com.example.dominantsoftdevelopment.model.baseData.BaseModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class ProductFeatures extends BaseModel {
    @OneToOne
    Product product;
    @ManyToOne
    ProductFeatureName productFeatureName;
    @ManyToOne
    ProductFeatureValue productFutureValue;
    String value;
}
