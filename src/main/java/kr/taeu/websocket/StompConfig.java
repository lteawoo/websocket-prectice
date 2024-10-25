package kr.taeu.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {
    private final StompInterceptor stompInterceptor;

    public StompConfig(StompInterceptor stompInterceptor) {
        this.stompInterceptor = stompInterceptor;
    }

    /**
     * 클라이언트에 STOMP 연결 엔드포인트 제공
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp/default").setAllowedOrigins("localhost");

        registry.addEndpoint("/stomp/sockjs").setAllowedOrigins("localhost").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트가 서버로 메시지를 보낼 때 사용할 경로의 접두사
        registry.setApplicationDestinationPrefixes("/app");
        // 내장 심플브로커를 활성화, topic과 queue 접두사가 붙은 메시지는 심플브로커가 구독자에게 전달
        registry.enableSimpleBroker("/topic", "/queue"); // 예로 topic은 브로드캐스트, queue는 개별 사용자나 특정 큐에 전달 하기 위함
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompInterceptor);
    }
}
