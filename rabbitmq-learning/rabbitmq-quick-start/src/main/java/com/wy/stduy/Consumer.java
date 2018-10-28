package com.wy.stduy;

import com.rabbitmq.client.*;
import com.wy.stduy.manager.RabbitMqManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.wy.stduy.config.RabbitMqConfig.*;


public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Connection connection = null;
        Channel channel = null;

        Address[] addresses = {new Address(HOST_IP, PORT)};
        try {
            //消费者创建连接方式与生成者略有不同
            connection = RabbitMqManager.createConnection(addresses);

            channel = connection.createChannel();
            //设置客户端最多接收未被ack的消息个数
            channel.basicQos(BASIC_QOS);

            Channel finalChannel = channel;
            DefaultConsumer consumer = new DefaultConsumer(finalChannel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                    String message = new String(body);
                    LOGGER.info("receive message:{}", message);

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Throwable e) {
                        LOGGER.error("延时消费被中断", e);
                    }
                    finalChannel.basicAck(envelope.getDeliveryTag(), false);
                }
            };
            channel.basicConsume(QUEUE_NAME, consumer);
            //等待回调函数执行完毕之后，关闭资源
            TimeUnit.SECONDS.sleep(5);

        } finally {
            RabbitMqManager.close(connection, channel);
        }

    }

}
