package com.dmitrybondarev.shop.util.mq;

public interface MessageEmitter {

    void produceMessage(String operationName, Long id);
    void produceMessage(String operationName);
}
