package com.example.dominantsoftdevelopment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponse {
    @JsonProperty("message")
    public String message;

    @JsonProperty("data")
    public TokenData data;

    @JsonProperty("token_type")
    public String tokenType;
}