package com.example.websocket.chat;

import com.example.websocket.chat.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@RequiredArgsConstructor
@Repository
public class ChatRepository {

    // Redis
    private final RedisTemplate<String, ChatMessage> redisChatMessageTemplate;
    private ListOperations<String, ChatMessage> opsListChatMessage;

    @PostConstruct
    private void init() {
        opsListChatMessage = redisChatMessageTemplate.opsForList();
    }

    public List<ChatMessage> findByChannelId(String channelId) {
        Long size = opsListChatMessage.size(channelId);
        List list = opsListChatMessage.range(channelId, 0, size);
        return list;
    }

    public void pushMessage(String channelId, ChatMessage chatMessage) {
        opsListChatMessage.rightPush(channelId, chatMessage);
    }

}
