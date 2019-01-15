package com.wy.study.websocket.socket;

import com.wy.study.websocket.domain.MySession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketRepository {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketRepository.class);

    private static final ConcurrentHashMap<String, MySession> repository = new ConcurrentHashMap<>(256);

    /**
     * 添加session管理，好处是后面可以主动获取指定session发送消息 token最好是一个相对固定的id
     */
    public static void addSession(String token, WebSocketSession session) {

        if (StringUtils.isEmpty(token)) {
            return;
        }

        MySession mySession = MySession.builder()
                .token(token)
                .session(session)
                .build();

        MySession old = repository.get(token);
        if (!Objects.isNull(old) && Objects.equals(session, old.getSession())) {
            return;
        }
        repository.put(token, mySession);
    }

    /**
     * 根据token获取连接
     */
    public static WebSocketSession getSession(String token) {
        MySession mySession = getMySession(token);
        if (Objects.isNull(mySession)) {
            return null;
        }
        return mySession.getSession();
    }

    public static MySession getMySession(String token) {
        return repository.get(token);
    }

    /**
     * 移除缓存
     */
    public static void removeSession(String token) {
        repository.remove(token);
    }

    /**
     * 直接remove session
     */
    public static void removeSession(WebSocketSession session) {
        repository.entrySet().removeIf(entry -> Objects.equals(entry.getValue().getSession(), session));
    }

    /**
     * 根据token关闭session
     *
     * @param token       token
     * @param closeReason 关闭理由
     */
    public static void closeSession(String token, CloseStatus closeReason) {
        MySession mySession = repository.get(token);
        if (Objects.isNull(mySession)) {
            return;
        }
        closeSession(mySession.getSession(), closeReason);
        removeSession(token);
    }

    /**
     * 关闭session
     *
     * @param session     session
     * @param closeReason 关闭理由
     */
    private static void closeSession(WebSocketSession session, CloseStatus closeReason) {

        if (Objects.isNull(session)) {
            return;
        }
        try {
            session.close(closeReason);
        } catch (Throwable e) {
            logger.error("close session error", e);
        }
    }


}
