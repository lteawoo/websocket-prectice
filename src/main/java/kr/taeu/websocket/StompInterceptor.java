package kr.taeu.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

@Slf4j
@Configuration
public class StompInterceptor implements ChannelInterceptor {



    /**
     * 메시지가 채널로 전송되기 전
     * @param message
     * @param channel
     * @return
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("StompInterceptor preSend msg: {}", message);
        return ChannelInterceptor.super.preSend(message, channel);
    }
}
