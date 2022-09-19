package com.example.websocket.handler;

import com.example.websocket.channel.ChannelService;
import com.example.websocket.chat.ChatService;
import com.example.websocket.chat.dto.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;
    private final ChannelService channelService;


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        channelService.getDefaultChannel()
                .handleActions(session, chatMessage, chatService);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("welcome!!! green house! :)"));
        //TODO: session 에서 token으로 사용자 가져오기.
    }
}
