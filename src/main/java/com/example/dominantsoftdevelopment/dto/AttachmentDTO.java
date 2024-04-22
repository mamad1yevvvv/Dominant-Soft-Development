package com.example.dominantsoftdevelopment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDTO {

    private Long id;

    private String originalName;

    private String url;
}

