package com.wy.stduy.config;

public class RabbitMqConfig {

    public static final String EXCHANGE_NAME = "exchange_demo";
    public static final String ROUTING_KEY = "routingkey_demo";
    public static final String QUEUE_NAME = "queue_demo";
    public static final String HOST_IP = "127.0.0.1";
    public static final Integer PORT = 5672;

    public static final String USER_NAME = "demo";
    public static final String PASS_WORD = "demo123";

    public static final String EXCHANGE_TYPE = "direct";

    public static Integer BASIC_QOS = 64;

}
