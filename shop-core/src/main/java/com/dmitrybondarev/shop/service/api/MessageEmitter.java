package com.dmitrybondarev.shop.service.api;

public interface MessageEmitter {

    void produceMessage(String operationName, Long id);
    void produceMessage(String operationName);
}
