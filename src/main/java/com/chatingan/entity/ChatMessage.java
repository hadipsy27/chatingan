package com.chatingan.entity;

import com.chatingan.model.MessageStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String chatId;
    private String recipientId;
    private String recipientName;
    private String senderId;
    private String senderName;
    private String content;
    private Date createAt;
    @Enumerated(EnumType.STRING)
    private MessageStatus status;
}
