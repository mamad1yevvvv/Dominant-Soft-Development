package com.example.dominantsoftdevelopment.notification;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationMessagingService {

    private final FirebaseMessaging firebaseMessaging;

    public String sendWebNotificationByToken(NotificationMessageDto notificationMessage) {

        Notification notification = getNotification(notificationMessage);

        Message message = Message
                .builder()
                .setToken(notificationMessage.getRecipientToken())
                .setNotification(notification)
                .putAllData(notificationMessage.getData())
                .setWebpushConfig(WebpushConfig.builder().build())
                .build();

        return getMessage(message);
    }


    public String sendAndroidNotificationByToken(NotificationMessageDto notificationMessage) {

        AndroidNotification notification = AndroidNotification
                .builder()
                .setBody(notificationMessage.getBody())
//                .setChannelId()
                .setTitle(notificationMessage.getTitle())
                .setImage(notificationMessage.getIcon()).build();

        Message message = Message
                .builder()
                .setToken(notificationMessage.getRecipientToken())
                .putAllData(notificationMessage.getData())
                .setAndroidConfig(AndroidConfig.builder().setNotification(notification).build())
                .build();

        return getMessage(message);
    }

    private String getMessage(Message message) {
        try {
            firebaseMessaging.send(message);
            return "Success sending notification";
        } catch (FirebaseMessagingException e) {
            return "Error sending notification";
        }
    }

    private static Notification getNotification(NotificationMessageDto notificationMessage) {
        return Notification
                .builder()
                .setTitle(notificationMessage.getTitle())
                .setBody(notificationMessage.getBody())
                .setImage(notificationMessage.getIcon())
                .build();
    }

}

