package com.emeka.rabbitmq.producer.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * This Publisher increments the value of the counter variable.
 * The method sets the counter as a message to RabbitMQ
 */
@Service
public class FixedRateProducer {

    @Autowired
    private RabbitTemplate template;
    private int counter = 0;
    private static final Logger LOGGER = LoggerFactory.getLogger(FixedRateProducer.class);

    // this method publishes message at every 500 ms
    @Scheduled(fixedRate = 500)
    public void sendMessage(){
        counter ++;
//        LOGGER.info("Counter is {}",counter);
        template.convertAndSend("emeka.fixedrate", "Fixed Rate: "+counter);
    }
}
