package com.example.dominantsoftdevelopment.otp;


import com.example.dominantsoftdevelopment.model.Attachment;
import com.example.dominantsoftdevelopment.model.enums.Roles;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@RedisHash(timeToLive = 3600 * 7)
@Builder
public class OTP  {
    private String phoneNumber;

    private String firstname;

    private String lastname;

    @Id
    private String email;

    private String lastName;

    private String password;

    private String code;

    private boolean accountNonLocked = true;

    private Attachment attachment;

    private Roles roles;

    private LocalDateTime sendTime;

    private int sentCount;
}
