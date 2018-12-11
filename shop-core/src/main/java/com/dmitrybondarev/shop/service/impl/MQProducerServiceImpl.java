package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.service.api.MQProducerService;
import com.dmitrybondarev.shop.util.logging.Loggable;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Service
public class MQProducerServiceImpl implements MQProducerService {

    private static final String EXCHANGE_NAME = "shop-core";
    private static final Logger logger = Logger.getLogger(MQProducerServiceImpl.class);

    @Override
    @Loggable
    public void produceMessage(String msg) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("rabbitmq");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(EXCHANGE_NAME, false, false, false, null);
            channel.basicPublish("", EXCHANGE_NAME, null, msg.getBytes(StandardCharsets.UTF_8));

            logger.info(" [ X ] PRODUCE MESSAGE:  \' " + msg + " \'");
        }
    }
}
