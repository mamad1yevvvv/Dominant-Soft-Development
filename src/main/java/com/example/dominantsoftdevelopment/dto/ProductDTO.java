package com.example.dominantsoftdevelopment.dto;

import com.example.dominantsoftdevelopment.model.Attachment;
import com.example.dominantsoftdevelopment.model.enums.ConditionProduct;
import com.example.dominantsoftdevelopment.model.enums.PayType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {
    @JsonProperty("productId")
    Long id;
    String productName;
    Double price;
    PayType payType;
    String productBrand;
    ConditionProduct conditionProduct;
    String description;
    List<Attachment> attachment;
}
