package com.example.dominantsoftdevelopment.service.auth;

import com.example.dominantsoftdevelopment.config.jwtFilter.JWTProvider;
import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.LoginDTO;
import com.example.dominantsoftdevelopment.dto.RegisterDTO;
import com.example.dominantsoftdevelopment.dto.TokenDTO;
import com.example.dominantsoftdevelopment.exceptions.RestException;
import com.example.dominantsoftdevelopment.model.User;
import com.example.dominantsoftdevelopment.model.enums.Roles;
import com.example.dominantsoftdevelopment.otp.OTP;
import com.example.dominantsoftdevelopment.repository.AttachmentRepository;
import com.example.dominantsoftdevelopment.repository.OTPRepository;
import com.example.dominantsoftdevelopment.repository.UserRepository;
import com.example.dominantsoftdevelopment.service.SendSMS.SendSMSService;
import com.example.dominantsoftdevelopment.service.emailService.EmailService;
import com.example.dominantsoftdevelopment.utils.AppConstants;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AttachmentRepository attachmentRepository;
    private final SendSMSService sendSMSService;
    private final OTPRepository otpRepository;

    public AuthServiceImpl(UserRepository userRepository,
                           @Lazy AuthenticationManager authenticationManager,
                           PasswordEncoder passwordEncoder, JWTProvider jwtProvider,
                           AttachmentRepository attachmentRepository, SendSMSService sendSMSService,
                           OTPRepository otpRepository) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.attachmentRepository = attachmentRepository;
        this.sendSMSService = sendSMSService;
        this.otpRepository = otpRepository;
    }

    @Override
    public ApiResult<TokenDTO> login(LoginDTO loginDTO) {
        User user1 = checkCredential(loginDTO.phoneNumber(), loginDTO.password());
        return ApiResult.successResponse(generateTokenDTO(user1));
    }

    @Override
    public ApiResult<TokenDTO> refreshToken(String accessToken, String refreshToken) {
        if (!accessToken.startsWith(AppConstants.BEARER_TYPE))
            throw RestException.restThrow("Bearer emas");

        accessToken = accessToken.substring(AppConstants.BEARER_TYPE.length()).trim();
        refreshToken = refreshToken.substring(AppConstants.BEARER_TYPE.length()).trim();
        if (!jwtProvider.isExpired(accessToken))
            throw RestException.restThrow("Token muddati tugamagan");

        if (!jwtProvider.validRefreshToken(refreshToken))
            throw RestException.restThrow("Refresh token valid emas");

        String userId = jwtProvider.extractUserIdFromRefreshToken(refreshToken);
        User user = findUserById(Long.valueOf(userId))
                .orElseThrow(() -> RestException.restThrow("User not found: " + userId, HttpStatus.NOT_FOUND));

        TokenDTO tokenDTO = generateTokenDTO(user);

        return ApiResult.successResponse(tokenDTO);
    }

    @Override
    public ApiResult<?> register(RegisterDTO registerDTO) {
       /* OTP otp = otpRepository.findByPhoneNumber(registerDTO.phoneNumber())
                .orElseThrow(() -> RestException.restThrow("PhoneNumber not found wrong sms code", HttpStatus.BAD_REQUEST));

         if (userRepository.findByPhoneNumber(registerDTO.phoneNumber()).isPresent()){
            throw RestException.restThrow("User already exsist",HttpStatus.BAD_REQUEST);
        }
        */

        // authenticating with email
        OTP otp = otpRepository.findByEmail(registerDTO.email())
                .orElseThrow(() -> RestException.restThrow("Email not found or  wrong email code", HttpStatus.BAD_REQUEST));

        if (userRepository.findByEmail(registerDTO.email()).isPresent()){
            throw RestException.restThrow("User already exsist",HttpStatus.BAD_REQUEST);
        }
        if (!registerDTO.code().equals(Integer.parseInt(otp.getCode()))){
            throw RestException.restThrow("Wrong sms code", HttpStatus.BAD_REQUEST);
        }
        if (otp.getSendTime().plusMinutes(3).isBefore(LocalDateTime.now())){
            throw RestException.restThrow("Code expired",HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .firstName(registerDTO.firstName())
                .lastName(registerDTO.lastName())
                .email(registerDTO.email())
                .password(passwordEncoder.encode(registerDTO.password()))
                .phoneNumber(registerDTO.phoneNumber())
                .roles(Roles.USER)
                .build();
        userRepository.save(user);

        return ApiResult.successResponse(generateTokenDTO(user));

    }


    @Override
    public ApiResult<Boolean> sendEmail(String email) {
        String generationCode = EmailService.getGenerationCode();

        if (!EmailService.sendMessageToEmail(email,generationCode)){
            throw RestException.restThrow("send email wrong",HttpStatus.BAD_REQUEST);
        }
        System.out.println(generationCode);

        otpRepository.save(OTP.builder().email(email).code(generationCode).sendTime(LocalDateTime.now()).build());
        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> sendSms(String phoneNumber) {
        String code = EmailService.getGenerationCode();

        Boolean b = sendSMSService.sendSMS(phoneNumber, code);
        System.out.println(b);
//        if (!b) {
//            throw RestException.restThrow("send sms wrong",HttpStatus.BAD_REQUEST);
//        }

        System.out.println(code);

        otpRepository.save(OTP.builder().phoneNumber(phoneNumber).code(code).sendTime(LocalDateTime.now()).build());
        return ApiResult.successResponse(true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public Optional<User> findUserById(Long userId) {
        if (userId == null)
            return Optional.empty();
        return userRepository.findById(userId);
    }

    private TokenDTO generateTokenDTO(User user) {
        String id = user.getId().toString();
        String accessToken = jwtProvider.createAccessToken(id);
        String refreshToken = jwtProvider.createRefreshAccessToken(id);
        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public User checkCredential(String username, String password) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return (User) authentication.getPrincipal();
    }
}
