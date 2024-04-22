package com.example.dominantsoftdevelopment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO (
        @NotBlank  String phoneNumber, @Email String email, @NotBlank String password,
        @NotBlank String firstName, @NotBlank String lastName , Integer code , Long photo_id){
}
