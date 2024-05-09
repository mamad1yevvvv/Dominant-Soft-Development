package com.example.dominantsoftdevelopment.dto;

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
public class AddOrderDTO {
    List<AddOrderItemDTO> orderItems;
    PayType payType;
}