package com.dmitrybondarev.shop.service.impl;

import com.dmitrybondarev.shop.service.api.MQProducerService;
import com.dmitrybondarev.shop.service.api.MessageEmitter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class MessageEmitterImpl implements MessageEmitter {

    @Autowired
    private MQProducerService mqProducerService;

    private static final Logger logger = Logger.getLogger(MessageEmitterImpl.class);

    @Override
    public void produceMessage(String operation, Long id) {
        try {
            mqProducerService.produceMessage(operation + " , id=" + id);
        } catch (IOException ioe) {
            logger.error("IOException during MQ producing: " + ioe.getMessage());
        } catch (TimeoutException te) {
            logger.error("TimeoutException during MQ producing: " + te.getMessage());
        }
    }

    @Override
    public void produceMessage(String operation) {
        try {
            mqProducerService.produceMessage(operation);
        } catch (IOException ioe) {
            logger.error("IOException during MQ producing: " + ioe.getMessage());
        } catch (TimeoutException te) {
            logger.error("TimeoutException during MQ producing: " + te.getMessage());
        }
    }
}
