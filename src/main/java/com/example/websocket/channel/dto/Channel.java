package com.example.websocket.channel.dto;

import com.example.websocket.chat.ChatService;
import com.example.websocket.chat.dto.ChatMessage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Channel {
    private final String channelId;
    private final String name;
    private final Set<WebSocketSession> sessions = new HashSet<>();

    @Builder(access = AccessLevel.PRIVATE)
    private Channel(String channelId, String name) {
        this.channelId = channelId;
        this.name = name;
    }

    public static Channel create(String channelId, String name) {
        return builder()
                .channelId(channelId)
                .name(name)
                .build();
    }

    public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
        }
        sendMessage(chatMessage, chatService);
    }

    public <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
}