package com.example.dominantsoftdevelopment.chat.chat_room;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ChatRoom {

    @Id
    private Long id;
    private Long chatId;
    private Long senderId;
    private Long recipientId;
}
