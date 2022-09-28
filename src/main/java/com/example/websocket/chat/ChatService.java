package com.example.websocket.chat;

import com.example.websocket.chat.dto.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private final ChatRepository chatRepository;

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            String payload = objectMapper.writeValueAsString(message);
            session.sendMessage(new TextMessage(payload));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void pushMessage(String channelId, ChatMessage chatMessage) {
        chatRepository.pushMessage(channelId, chatMessage);
    }

    public List<ChatMessage> getMessageList(String channelId) {
        return chatRepository.findByChannelId(channelId);
    }

}