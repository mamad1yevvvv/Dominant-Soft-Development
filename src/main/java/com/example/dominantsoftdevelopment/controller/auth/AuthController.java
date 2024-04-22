package com.example.dominantsoftdevelopment.controller.auth;

import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.LoginDTO;
import com.example.dominantsoftdevelopment.dto.RegisterDTO;
import com.example.dominantsoftdevelopment.dto.TokenDTO;
import com.example.dominantsoftdevelopment.utils.AppConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(AuthController.BASE_PATH)
public interface AuthController {
    String BASE_PATH = "/api/auth";
    String LOGIN_PATH = "/login";
    String REFRESH_TOKEN_PATH = "/refresh-token";
    String REGISTER_PATH = "/register";
    String SEND_EMAIL = "/email";
    String SEND_SMS = "/sms";

    @PostMapping(LOGIN_PATH)
    HttpEntity<ApiResult<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO);

    @PatchMapping(REFRESH_TOKEN_PATH)
    HttpEntity<ApiResult<TokenDTO>> refreshToken(
            @RequestHeader(AppConstants.AUTH_HEADER) String accessToken,
            @RequestHeader(AppConstants.REFRESH_AUTH_HEADER) String refreshToken
    );

    @PostMapping(REGISTER_PATH)
    HttpEntity<ApiResult<?>> register(@Valid @RequestBody RegisterDTO registerDTO);

    @PostMapping(SEND_EMAIL)
    HttpEntity<ApiResult<Boolean>> sendEmail( @Valid @Email @RequestParam String email);

    @PostMapping(SEND_SMS)
    HttpEntity<ApiResult<Boolean>> sendSMS(@RequestParam String phoneNumber);



}
