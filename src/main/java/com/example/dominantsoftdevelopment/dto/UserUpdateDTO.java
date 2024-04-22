package com.example.dominantsoftdevelopment.dto;

import com.example.dominantsoftdevelopment.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    String firstName;
    String lastName;
    Address address;
}
