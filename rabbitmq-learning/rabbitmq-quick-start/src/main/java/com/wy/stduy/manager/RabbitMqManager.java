package com.wy.stduy.manager;

import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.wy.stduy.config.RabbitMqConfig.*;

public class RabbitMqManager {

    public static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqManager.class);
    public volatile static ConnectionFactory factory;
    public static final Object lock = new Object();

    private static ConnectionFactory buildConnectionFactory() {
        if (null != factory) {
            return factory;
        }
        synchronized (lock) {
            if (null != factory) {
                return factory;
            }
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(HOST_IP);
            connectionFactory.setPort(PORT);
            connectionFactory.setUsername(USER_NAME);
            connectionFactory.setPassword(PASS_WORD);
            factory = connectionFactory;
        }
        return factory;
    }

    public static Connection createConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = buildConnectionFactory();
        return connectionFactory.newConnection();
    }

    public static Connection createConnection(Address[] addresses) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = buildConnectionFactory();
        return connectionFactory.newConnection(addresses);
    }

    public static void close(Connection connection, Channel channel) {
        close(channel);
        close(connection);
    }

    public static void close(Connection connection) {
        try {
            if (null != connection) {
                connection.close();
            }
        } catch (Throwable e) {
            LOGGER.error("close connection error", e);
        }
    }

    public static void close(Channel channel) {
        try {
            if (null != channel) {
                channel.close();
            }
        } catch (Throwable e) {
            LOGGER.error("close channel error", e);
        }
    }

}
