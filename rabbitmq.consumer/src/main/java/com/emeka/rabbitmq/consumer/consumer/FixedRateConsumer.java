package com.emeka.rabbitmq.consumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

//@Service
public class FixedRateConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(FixedRateConsumer.class);

    // Set 3 concurrent consumers for the queue
    @RabbitListener(queues = "emeka.fixedrate", concurrency = "3-7")
    public void listen(String message) throws InterruptedException {
        // Random delay up to 2 seconds
        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(1000,2000));
        LOGGER.info("{} : Consuming {}",Thread.currentThread().getName(),message);
    }

}
