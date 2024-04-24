package com.example.dominantsoftdevelopment.dto;

import com.example.dominantsoftdevelopment.model.Attachment;
import com.example.dominantsoftdevelopment.model.Category;
import com.example.dominantsoftdevelopment.model.enums.ConditionProduct;
import com.example.dominantsoftdevelopment.model.enums.PayType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTOList {
    Long id;
    String productName;
    Double price;
    Category productCategory;
    Boolean availability;
    PayType payType;
    ConditionProduct conditionProduct;
    String description;
    UserDTO seller;
    List<Attachment> attachment;
    List<ProductFeaturesDTO> productDTOLists;
}
