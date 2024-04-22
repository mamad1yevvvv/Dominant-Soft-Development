package com.example.dominantsoftdevelopment.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(@NotBlank String phoneNumber, @NotBlank String password) {
}

