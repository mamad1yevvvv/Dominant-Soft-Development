package com.example.dominantsoftdevelopment.controller.auth;

import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.LoginDTO;
import com.example.dominantsoftdevelopment.dto.RegisterDTO;
import com.example.dominantsoftdevelopment.dto.TokenDTO;
import com.example.dominantsoftdevelopment.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public HttpEntity<ApiResult<TokenDTO>> login(LoginDTO loginDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authService.login(loginDTO));
    }

    @Override
    public HttpEntity<ApiResult<TokenDTO>> refreshToken(String accessToken, String refreshToken) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authService.refreshToken(accessToken,refreshToken));
    }

    @Override
    public HttpEntity<ApiResult<?>> register(RegisterDTO registerDTO) {
        return ResponseEntity.ok(authService.register(registerDTO));
    }

    @Override
    public HttpEntity<ApiResult<Boolean>> sendEmail(String email) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authService.sendEmail(email));
    }

    @Override
    public HttpEntity<ApiResult<Boolean>> sendSMS(String phoneNumber) {
        return ResponseEntity.ok(authService.sendSms(phoneNumber));
    }
}
