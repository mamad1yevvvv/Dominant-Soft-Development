package com.example.dominantsoftdevelopment.model;

import com.example.dominantsoftdevelopment.model.baseData.BaseModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ProductFeatureName extends BaseModel {
    @Column(nullable = false)
    String name;
    String measure;
    @OneToMany(mappedBy = "productFeatureName")
    List<ProductFeatureValue> productFutureValues;//bu faqat mappetby uchun yozilgan
    @ManyToOne
    Category category;
}
