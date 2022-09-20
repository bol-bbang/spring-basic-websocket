package com.example.websocket.channel.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Channel {
    private final String channelId;
    private final String name;

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

}