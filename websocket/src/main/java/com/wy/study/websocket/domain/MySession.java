package com.wy.study.websocket.domain;

import org.springframework.web.socket.WebSocketSession;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MySession {

    private String token;
    private WebSocketSession session;

}
