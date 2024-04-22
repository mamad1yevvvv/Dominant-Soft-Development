package com.example.dominantsoftdevelopment.dto;

import com.example.dominantsoftdevelopment.model.Address;
import com.example.dominantsoftdevelopment.model.Attachment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    Long id;
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    Address address;
    Attachment attachment;

}
