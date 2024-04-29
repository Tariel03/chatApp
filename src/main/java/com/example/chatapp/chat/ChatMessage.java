package com.example.chatapp.chat;

import lombok.*;

import java.awt.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private String content;
    private String sender;
    private MessageType type;

}
