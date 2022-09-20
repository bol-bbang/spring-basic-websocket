package com.example.websocket.channel.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class Channel implements Serializable {
    private static final long serialVersionUID = 6494678977089006639L;

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