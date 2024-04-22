package com.example.dominantsoftdevelopment.dto;

import com.example.dominantsoftdevelopment.model.Attachment;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDTO {
   Long id;
   String name;
   Attachment attachment;
   List<CategoryDTO> categoryDTOList;
}
