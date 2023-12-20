package com.chatingan.Repository;

import com.chatingan.entity.ChatMessage;
import com.chatingan.model.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {

    Long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, MessageStatus status);
    List<ChatMessage> findByChatId(String chatId);

    @Modifying
    @Query("UPDATE ChatMessage c SET c.status = :status WHERE c.senderId = :senderId AND c.recipientId = :recipientId")
    List<ChatMessage> updateStatus(
            @Param("status") MessageStatus status,
            @Param("senderId") String senderId,
            @Param("recipientId") String recipientId
    );
}
