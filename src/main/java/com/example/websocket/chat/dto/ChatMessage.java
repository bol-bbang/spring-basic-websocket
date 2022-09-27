package com.example.websocket.chat.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ChatMessage  implements Serializable {
    private static final long serialVersionUID = 6494678977089006639L;

    // 메시지 타입 : 입장, 채팅
    public enum MessageType {
        ENTER, TALK
    }
    private MessageType type; // 메시지 타입
    private String channelId; // 채널 고유 ID
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
}
