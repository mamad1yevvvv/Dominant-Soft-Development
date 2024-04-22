package com.example.dominantsoftdevelopment.chat.chat;

import com.example.dominantsoftdevelopment.chat.chat_room.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService
{
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService service;

    public ChatMessage save(ChatMessage chatMessage){

        var chatId = service.getChatroomId(
                chatMessage.getSenderId(),
                chatMessage.getRecipientId(),
                true
        ).orElseThrow();

        chatMessage.setChatId(chatId);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(Long senderId , Long recipientId){

        var chatId = service.getChatroomId(senderId , recipientId , false);
        return chatId.map(chatMessageRepository :: findByChatId).orElse(new ArrayList<>());
    }
}
