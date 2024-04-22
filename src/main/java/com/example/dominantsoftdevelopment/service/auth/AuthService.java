package com.example.dominantsoftdevelopment.service.auth;

import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.LoginDTO;
import com.example.dominantsoftdevelopment.dto.RegisterDTO;
import com.example.dominantsoftdevelopment.dto.TokenDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    ApiResult<TokenDTO> login(LoginDTO loginDTO);

    ApiResult<TokenDTO> refreshToken(String accessToken,String refreshToken);

    ApiResult<?> register(RegisterDTO registerDTO);

    ApiResult<Boolean> sendEmail(String email);

    ApiResult<Boolean> sendSms(String phoneNumber);
}
