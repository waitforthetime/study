package com.wy.study.websocket.service.impl;

import com.wy.study.websocket.service.TestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    private static final Logger logger = LoggerFactory.getLogger(TestService.class);

    private static String error = "error";

    @Override
    public void testHandleMessage(String message) {
        logger.info("message:{}", message);

        if (error.equals(message)) {
            throw new RuntimeException("测试下异常场景");
        }
    }
}
