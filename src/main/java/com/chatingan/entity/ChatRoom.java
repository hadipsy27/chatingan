package com.chatingan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
}
