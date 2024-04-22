package com.example.dominantsoftdevelopment.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddOrderItemDTO {

    private Long productId;

    private Double price;

    private Integer productCount;

}
