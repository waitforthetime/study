package com.wy.stduy;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.wy.stduy.manager.RabbitMqManager;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.wy.stduy.config.RabbitMqConfig.*;

public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = null;
        Channel channel = null;
        try {
            connection = RabbitMqManager.createConnection();
            channel = connection.createChannel();
            //创建一个type="direct"、持久化的、非自动删除的交换器
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE, true, false, null);
            //创建一个持久化的、非排他的、非自动删除的队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            //将交换器与队列通过路由键绑定
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

            String message = "Hello World!";
            //发送一条持久化的消息
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        } finally {
            RabbitMqManager.close(connection, channel);
        }

    }
}
