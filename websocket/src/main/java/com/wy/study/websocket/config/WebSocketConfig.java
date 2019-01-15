package com.wy.study.websocket.config;

import com.wy.study.websocket.handler.TestHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.ServletWebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.util.UrlPathHelper;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);
    private static final String TEST_PATH = "websocket/test";
    private static final Integer MAX_SIZE = 32768;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        ServletWebSocketHandlerRegistry servletWebSocketHandlerRegistry = (ServletWebSocketHandlerRegistry) webSocketHandlerRegistry;
        servletWebSocketHandlerRegistry.setOrder(2);
        servletWebSocketHandlerRegistry.setUrlPathHelper(new UrlPathHelper());
        webSocketHandlerRegistry.addHandler(testHandler(), TEST_PATH)
                .setAllowedOrigins("*")
                .withSockJS();
    }


    @Bean
    public TestHandler testHandler() {
        return new TestHandler();
    }

    @Bean
    public ServletServerContainerFactoryBean genServletBean() {
        ServletServerContainerFactoryBean servletServerContainerFactoryBean = new ServletServerContainerFactoryBean();
        servletServerContainerFactoryBean.setMaxBinaryMessageBufferSize(MAX_SIZE);
        servletServerContainerFactoryBean.setMaxTextMessageBufferSize(MAX_SIZE);
        return servletServerContainerFactoryBean;
    }
}



