package com.example.websocket.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ObjectMapper objectMapper;

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            String payload = objectMapper.writeValueAsString(message);
            session.sendMessage(new TextMessage(payload));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}