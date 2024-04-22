package com.example.dominantsoftdevelopment.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class  NotificationMessageDto {
    private String recipientToken;
    private String title;
    private String body;
    private String icon;
    private Map<String, String> data;
}
