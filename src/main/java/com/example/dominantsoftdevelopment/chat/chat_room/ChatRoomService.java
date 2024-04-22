package com.example.dominantsoftdevelopment.chat.chat_room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final  ChatRoomRepository chatRoomRepository;
    public Optional<Long> getChatroomId(Long senderId ,
                                        Long recipientId ,
                                        boolean createNewRoomIfNotExists)
    {
    return chatRoomRepository.findBySenderIdAndRecipientId(senderId ,recipientId  )
            .map(ChatRoom::getChatId)
            .or(
            () -> {
                if (createNewRoomIfNotExists) {
                    var chatId = createChatId(senderId , recipientId);
                    return Optional.of(chatId);
                }
                return Optional.empty();
            }
    );

    }

    private Long createChatId(Long senderId, Long recipientId) {

        var chatId = String.format("%s_%s" , senderId , recipientId);
        ChatRoom  senderRecipient = ChatRoom.builder()
                .chatId(Long.valueOf(chatId))
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        ChatRoom  recipientSender = ChatRoom.builder()
                .chatId(Long.valueOf(chatId))
                .recipientId(recipientId)
                .senderId(senderId)
                .build();

        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);
        return Long.valueOf(chatId);
    }
}
