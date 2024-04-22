package com.example.dominantsoftdevelopment.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private ProductDTO product;

    private Double price;

    private Integer productCount;

}
