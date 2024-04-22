package com.example.dominantsoftdevelopment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserResetPasswordDTO(@NotBlank(message = "phone cannot be null or blank") String phone,
                                   @Size(min = 8, max = 32, message = "password must be between {min} and {max}")
                                   @NotBlank(message = "password cannot be null or blank") String password,
                                   @NotBlank(message = "sms code cannot be null or blank") String smsCode) {}
