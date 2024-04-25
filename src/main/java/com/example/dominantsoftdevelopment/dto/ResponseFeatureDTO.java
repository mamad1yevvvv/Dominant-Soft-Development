package com.example.dominantsoftdevelopment.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseFeatureDTO {
    ProductFeatureNameDTO productFeatureNameDTO;
    List<ProductFeatureValueDTO> productFeatureValueDTOList;
    boolean isSelectable;
}
