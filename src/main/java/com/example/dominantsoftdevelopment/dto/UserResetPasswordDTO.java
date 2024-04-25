package com.example.dominantsoftdevelopment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserResetPasswordDTO(@NotBlank(message = "email cannot be null or blank") String email,
                                   @Size(min = 6, max = 32, message = "password must be between {min} and {max}")
                                   @NotBlank(message = "password cannot be null or blank") String password,
                                   @NotNull(message = "sms code cannot be null or blank") Integer smsCode) {}
