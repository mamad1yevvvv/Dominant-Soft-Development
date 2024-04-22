package com.example.dominantsoftdevelopment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddCategoryDTO {
    @NotBlank
    String name;
    Long categoryId;
    Long attachmentId;
}
