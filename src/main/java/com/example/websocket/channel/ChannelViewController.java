package com.example.websocket.channel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChannelViewController {
    // 채팅 리스트 화면
    @GetMapping("/channel")
    public String viewChannel() {
        return "/chat/channel";
    }

    // 채팅방 입장 화면
    @GetMapping("/channel/enter/{channelId}")
    public String viewChannelDetail(Model model, @PathVariable String channelId) {
        model.addAttribute("channelId", channelId);
        return "/chat/channeldetail";
    }
}
