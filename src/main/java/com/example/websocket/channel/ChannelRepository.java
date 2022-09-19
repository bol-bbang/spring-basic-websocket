package com.example.websocket.channel;

import com.example.websocket.channel.dto.Channel;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ChannelRepository {
    private final Map<String, Channel> channelMap;

    private ChannelRepository() {
        channelMap = new LinkedHashMap<>();
    }

    public List<Channel> findAllChannelReverse() {
        // 채팅방 생성순서 최근 순으로 반환
        List<Channel> channels = new ArrayList<>(channelMap.values());
        Collections.reverse(channels);
        return channels;
    }

    public Optional<Channel> findChannelById(String id) {
        return Optional.ofNullable(channelMap.get(id));
    }

    public Channel createChannel(Channel channel) {
        channelMap.put(channel.getChannelId(), channel);
        return channel;
    }
}