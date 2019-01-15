package com.wy.study.druid.util;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static Integer random() {
        return random(32);
    }

    public static Integer random(int seed) {
        return new Random().nextInt(seed);
    }

}
