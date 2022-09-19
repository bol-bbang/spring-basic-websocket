package com.example.websocket.channel;

import com.example.websocket.channel.dto.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChannelController {

    private final ChannelService channelService;


    // 모든 채팅방 목록 반환
    @GetMapping("/channels")
    public List<Channel> getChannelList() {
        return channelService.getChannelList();
    }

    // 특정 채팅방 조회
    @GetMapping("/channel/{channelId}")
    public Channel getChannelInformation(@PathVariable String channelId) {
        return channelService.getChannel(channelId);
    }

    // 채팅방 생성
    @PostMapping("/create-channel")
    public Channel createChannel(@RequestParam String name) {
        return channelService.createChannel(name);
    }

}
