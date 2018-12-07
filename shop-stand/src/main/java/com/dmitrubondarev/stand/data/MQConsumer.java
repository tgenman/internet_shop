package com.dmitrubondarev.stand.data;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class MQConsumer {

    private static final String EXCHANGE_NAME = "shop-core";
    private static final Logger logger = Logger.getLogger(MQConsumer.class);
    Channel channel;
    Connection connection;
    private DataStateListener listener = DataStateListener.getInstance();


    public void start() throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("rabbitmq");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(EXCHANGE_NAME, false, false, false, null);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                logger.info(" [x] Received " + message);
//                if (message.contains("Created") || message.contains("Delete")
//                        || message.contains("Updated") || message.contains("Update")
//                        || message.contains("Create")) {

//                    logger.info("DATA RECEIVED");
//                    listener.dataIsNotActual();
//                }
            }
        };
        channel.basicConsume(EXCHANGE_NAME, true, consumer);
    }


    public void stop() throws IOException, TimeoutException {
        connection.close();
        channel.close();
    }
}
