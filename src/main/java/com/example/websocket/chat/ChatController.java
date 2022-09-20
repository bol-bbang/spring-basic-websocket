package com.example.websocket.chat;

import com.example.websocket.chat.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER == message.getType()) {
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }
        messagingTemplate.convertAndSend("/sub/chat/channel/" + message.getChannelId(), message);
    }

}
