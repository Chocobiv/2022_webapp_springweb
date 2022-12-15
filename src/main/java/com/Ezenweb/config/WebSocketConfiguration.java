package com.Ezenweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration  //설정 어노테이션 [ 스프링부트 시작 시 beans에 등록 ]
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Autowired  //서버소켓 클래스 호출
    private ServerSocketHandler serverSocketHandler;

    @Override   //구현하기
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(serverSocketHandler, "/chat").setAllowedOrigins("*");
        //WebSocketHandler에 작성한 서버소켓 클래스로 설정하기, path : 서버소켓 엔드포인트
        //setAllowedOrigins(URL) : 해당 핸들러를 요청할 수 있는 URL [ * : 모든 URL 혹은 도메인 ]

    }
}
