package com.example.dominantsoftdevelopment.dto;

import com.example.dominantsoftdevelopment.model.enums.ConditionProduct;
import com.example.dominantsoftdevelopment.model.enums.PayType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddProductDTO {
    @NotBlank
    String productName;
    @Min(1)
    Double price;
    @NotNull
    Long productCategory;
    @NotNull
    Boolean availability;
    @NotNull
    PayType payType;
    @NotNull
    ConditionProduct conditionProduct;

    String description;

    @NotNull
    Long sellerId;

    List<Long> attachmentIds;

    List<ProductFuturesDTO> productFuturesDTOS;
}
