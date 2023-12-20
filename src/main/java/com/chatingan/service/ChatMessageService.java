package com.chatingan.service;

import com.chatingan.Exception.ResourceNotFoundException;
import com.chatingan.Repository.ChatMessageRepository;
import com.chatingan.entity.ChatMessage;
import com.chatingan.model.MessageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private ChatRoomService chatRoomService;


    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setId(UUID.randomUUID().toString());
        chatMessage.setStatus(MessageStatus.RECEIVED);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    private Long coutMessage(String senderId, String recipientId) {
        return chatMessageRepository.countBySenderIdAndRecipientIdAndStatus(senderId, recipientId, MessageStatus.RECEIVED);
    }

    private List<ChatMessage> findMessage(String senderId, String recipientId) {
        final Optional<String> chatId = chatRoomService.getChatId(senderId, recipientId, false);
        List<ChatMessage> chatMessages = chatId.map(id -> chatMessageRepository.findByChatId(id)).orElse(new ArrayList<>());

        if (!chatMessages.isEmpty()){
            chatMessageRepository.updateStatus(MessageStatus.DELIVERED, senderId, recipientId);
        }
        return chatMessages;
    }

    public ChatMessage findById(String id) {
        return chatMessageRepository.findById(id)
            .map(chatMessage -> {
                chatMessage.setStatus(MessageStatus.DELIVERED);
                return chatMessageRepository.save(chatMessage);
            })
            .orElseThrow(() -> {
                return new ResourceNotFoundException("Message not found");
            });
    }

}
