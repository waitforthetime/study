package com.wy.study.websocket.util;

import java.util.UUID;

/**
 * Created by owen on 16/8/30.
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
