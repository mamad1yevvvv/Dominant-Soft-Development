package com.example.dominantsoftdevelopment.dto;

import com.example.dominantsoftdevelopment.utils.AppConstants;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDTO {

    private String accessToken;
    private String refreshToken;
    @Builder.Default
    private final String tokenType = AppConstants.BEARER_TYPE;
    private UserDTO userDTO;
}
