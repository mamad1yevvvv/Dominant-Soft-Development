package com.example.dominantsoftdevelopment.notification;


import com.example.dominantsoftdevelopment.model.User;
import com.example.dominantsoftdevelopment.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationMessagingService notificationMessagingService;
    private final UserRepository repository;

    @PostMapping("/on-violation")
    public String sendOnViolationWeb(@RequestBody NotificationMessageDto notificationMessage) {
        return notificationMessagingService.sendWebNotificationByToken(notificationMessage);
    }


//    @PostMapping("/require-permission/{id}")
//    public String sendRequirePermissionAndroid(@RequestBody NotificationMessageDto notificationMessage, @PathVariable Integer id) {
//        return notificationMessagingService.sendAndroidNotificationByToken(notificationMessage);
//    }
//
//
//    @PostMapping("/alarm/{id}")
//    public String sendAlarmAndroid(@RequestBody NotificationMessageDto notificationMessage, @PathVariable Integer id) {
//        return notificationMessagingService.sendAndroidNotificationByToken(notificationMessage);
//    }

    @PostMapping("/refresh-token/{id}")
    public void refreshToken(@RequestBody String token , @PathVariable Long id){
        User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("user id = " + id + " not found"));
        user.setFirebaseToken(token);
    }
}
