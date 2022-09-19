package com.example.websocket.channel;

import com.example.websocket.channel.dto.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChannelService {
    private final ChannelRepository channelRepository;

    @PostConstruct
    private void init() {
        /* test용 채널 */
        Channel defaultChannel = Channel.create("green-house-abcd", "Green's House");
        channelRepository.createChannel(defaultChannel);
    }

    public List<Channel> getChannelList() {
        return channelRepository.findAllChannelReverse();
    }

    public Channel getChannel(String channelId) {
        return channelRepository.findChannelById(channelId)
                .orElseThrow(() -> new IllegalArgumentException("channel-key is wrong!"));
    }

    public Channel createChannel(String name) {
        String channelId = generateChannelId();
        Channel channel = Channel.create(channelId, name);
        return channelRepository.createChannel(channel);
    }

    private String generateChannelId() {
        while (true) {
            String channelId = UUID.randomUUID().toString();
            //channelId 중복체크
            boolean isEmpty = channelRepository.findChannelById(channelId).isEmpty();
            if (isEmpty) {
                return channelId;
            }
        }
    }

}
