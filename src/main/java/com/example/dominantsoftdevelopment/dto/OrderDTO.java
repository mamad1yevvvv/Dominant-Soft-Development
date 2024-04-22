package com.example.dominantsoftdevelopment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @JsonProperty("orderId")
    private Long id;

    @JsonProperty("orderedDate")
    private LocalDateTime createdAt;

    private Double totalPrice;

    private List<OrderItemDTO> orderItems;
}
