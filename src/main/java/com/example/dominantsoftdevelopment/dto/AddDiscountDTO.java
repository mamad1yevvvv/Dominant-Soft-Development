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
public class AddDiscountDTO {

    Long productId;
    int percentage;
    Boolean isActive;
    LocalDateTime finishedAt;
}
