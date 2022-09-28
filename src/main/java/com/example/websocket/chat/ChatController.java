package com.example.websocket.chat;

import com.example.websocket.channel.ChannelService;
import com.example.websocket.chat.dto.ChatMessage;
import com.example.websocket.redis.RedisPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final RedisPublisher redisPublisher;
    private final ChannelService channelService;
    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER == message.getType()) {
            channelService.enterChannel(message.getChannelId());
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }
        redisPublisher.publish(channelService.getTopic(message.getChannelId()), message);
    }

    @ResponseBody
    @GetMapping("/chat/list/{channel_id}")
    public List<ChatMessage> getMessageList(@PathVariable("channel_id") String channelId) {
        return chatService.getMessageList(channelId);
    }

}
