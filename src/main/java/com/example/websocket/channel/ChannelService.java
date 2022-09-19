package com.example.websocket.channel;

import com.example.websocket.channel.dto.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ChannelService {
    private final Channel defaultChannel;

    private ChannelService() {
        defaultChannel = Channel.create("green-house-abcd", "Green's House");
    }

    public Channel getDefaultChannel() {
        return defaultChannel;
    }

    public Channel createChannel(String name) {
        String channelId = UUID.randomUUID().toString();
        //TODO: channelId 중복체크 필요.
        return Channel.create(channelId, name);
    }

}
