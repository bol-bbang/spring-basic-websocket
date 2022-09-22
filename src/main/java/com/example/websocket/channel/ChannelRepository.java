package com.example.websocket.channel;

import com.example.websocket.channel.dto.Channel;
import com.example.websocket.redis.RedisSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ChannelRepository {
    // 채팅방(topic)에 발행되는 메시지를 처리할 Listener
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    // 구독 처리 서비스
    private final RedisSubscriber redisSubscriber;
    // Redis
    private static final String CHANNELS = "CHANNEL";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, Channel> opsHashChannel;

    // 채팅방의 대화 메시지를 발행하기 위한 redis topic 정보.
    // 서버별로 채팅방에 매치되는 topic정보를 Map에 넣어 channelId로 찾을수 있도록 한다.
    private Map<String, ChannelTopic> topics;


    @PostConstruct
    private void init() {
        opsHashChannel = redisTemplate.opsForHash();
        topics = new HashMap<>();
    }

    public List<Channel> findAllChannel() {
        // 순서없이 조회
        return opsHashChannel.values(CHANNELS);
    }

    public Optional<Channel> findChannelById(String channelId) {
        return Optional.ofNullable(opsHashChannel.get(CHANNELS, channelId));
    }

    public Channel createChannel(Channel channel) {
        opsHashChannel.put(CHANNELS, channel.getChannelId(), channel);
        return channel;
    }

    public void createTopic(String channelId) {
        ChannelTopic topic = new ChannelTopic(channelId);
        redisMessageListenerContainer.addMessageListener(redisSubscriber, topic);
        topics.put(channelId, topic);
    }

    public Optional<ChannelTopic> getTopic(String channelId) {
        return Optional.ofNullable(topics.get(channelId));
    }

}