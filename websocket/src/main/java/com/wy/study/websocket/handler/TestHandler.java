package com.wy.study.websocket.handler;

import com.wy.study.websocket.service.TestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.Resource;

public class TestHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(TestHandler.class);
    @Resource
    private TestService testService;
    /**
     * 在WebSocket协商成功并且WebSocket连接打开并准备好使用后调用。
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("afterConnectionEstablished");
    }

//    /**
//     * 在新的Websocket消息到达时调用
//     * @param session
//     * @param message
//     * @throws Exception
//     */
//    @Override
//    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        super.handleMessage(session, message);
//    }

    /**
     * 处理文本消息，业务处理层
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        testService.testHandleMessage(message.getPayload());
        logger.info("is last info:{}",message.isLast());
        session.sendMessage(new TextMessage("lalala, 我要开始回消息了啊"));
    }

    /**
     * 心跳消息处理
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        logger.info("pong message:{}", message.getPayload().toString());
    }

    /**
     * 处理底层WebSocket消息传输中的错误。
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.error("exception",exception);
    }

    /**
     * 在任何一方关闭WebSocket连接之后或在发生传输错误之后调用。虽然会话在技术上可能仍然是开放的，但取决于底层实现，此时不鼓励发送消息，并且很可能不会成功。
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("session close:{}", status.getCode());
    }

    /**
     * WebSocketHandler是否处理部分消息。如果此标志设置为true且基础WebSocket服务器支持部分消息，
     * 则可以拆分大型WebSocket消息或未知大小的消息，并且可以通过多次调用handleMessage（WebSocketSession，WebSocketMessage）来接收。
     * 标志WebSocketMessage.isLast（）指示消息是否是部分消息以及它是否是最后一部分。
     * @return
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
