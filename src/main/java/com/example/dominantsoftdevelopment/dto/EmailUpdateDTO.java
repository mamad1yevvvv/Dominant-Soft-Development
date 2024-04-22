package com.example.dominantsoftdevelopment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmailUpdateDTO (@NotBlank String newEmail, @NotNull Integer code){
}
