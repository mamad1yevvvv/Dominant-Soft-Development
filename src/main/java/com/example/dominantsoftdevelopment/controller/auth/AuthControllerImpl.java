package com.example.dominantsoftdevelopment.controller.auth;

import com.example.dominantsoftdevelopment.dto.*;
import com.example.dominantsoftdevelopment.repository.UserRepository;
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
    private final UserRepository userRepository;

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

    @Override
    public HttpEntity<Boolean> existByEmail(EmailDTO emailDTO) {
        return ResponseEntity.ok(userRepository.existsByEmail(emailDTO.getEmail()));
    }

    @Override
    public HttpEntity<Boolean> existByPhone(PhoneDTO phoneDTO) {
        return ResponseEntity.ok(userRepository.existsByPhoneNumber(phoneDTO.getPhone()));
    }
}
