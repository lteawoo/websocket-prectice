package kr.taeu.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class StompController {

    /**
     * /app/hello 경로로 전달되면 호출 setApplicationDestinationPrefixes 접두사는 생략
     * @param msg
     * @return
     */
    @MessageMapping("/hello")
    public String hello(String msg) {
        System.out.println("hello msg = " + msg);
        return "hello msg: " + msg;
    }

    /**
     * /app/hello-and-send 경로로 전달되면 수행하고, /topic 구독자에 전달
     */
    @MessageMapping("/hello-and-send")
    @SendTo("/topic")
    public String helloAndSend(String msg) {
        System.out.println("received msg = " + msg);
        return "received and send: " + msg;
    }
}
