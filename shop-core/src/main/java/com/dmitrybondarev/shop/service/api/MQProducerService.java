package com.dmitrybondarev.shop.service.api;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface MQProducerService {

    void produceMessage(String msg) throws IOException, TimeoutException;
}
