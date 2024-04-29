package com.example.chatapp.config;

import com.example.chatapp.chat.ChatMessage;
import com.example.chatapp.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messageSendingOperations;
    @EventListener
    public  void handleWebSocketDisconnectListener(SessionDisconnectEvent disconnectEvent){
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(disconnectEvent.getMessage());
        String username = (String) accessor.getSessionAttributes().get("username");
        if(username != null){
            log.info("user disconnected");
            ChatMessage chatMessage = ChatMessage.builder().type(MessageType.LEAVER).content(username)
                    .build();
            messageSendingOperations.convertAndSend("", chatMessage);

        }
    }
}
