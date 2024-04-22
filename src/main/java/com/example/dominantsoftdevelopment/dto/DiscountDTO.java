package com.example.dominantsoftdevelopment.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiscountDTO {
    Long id;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    boolean deleted;
    int percentage;
    ProductDTO productId;
    Boolean isActive;
    LocalDateTime finishedAt;
}
