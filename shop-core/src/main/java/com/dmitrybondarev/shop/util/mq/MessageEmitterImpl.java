package com.dmitrybondarev.shop.util.mq;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class MessageEmitterImpl implements MessageEmitter {

    private MQProducerService mqProducerService;

    public MessageEmitterImpl(MQProducerService mqProducerService) {
        this.mqProducerService = mqProducerService;
    }

    private static final Logger logger = Logger.getLogger(MessageEmitterImpl.class);

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
