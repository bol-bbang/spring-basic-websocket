package com.example.websocket.redis;

import com.example.websocket.chat.ChatService;
import com.example.websocket.chat.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisPublisher {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ChatService chatService;

    public void publish(ChannelTopic topic, ChatMessage message) {
        chatService.pushMessage(message.getChannelId(), message);
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}