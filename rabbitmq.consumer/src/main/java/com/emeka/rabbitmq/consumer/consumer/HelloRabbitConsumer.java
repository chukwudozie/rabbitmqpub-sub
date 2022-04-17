package com.emeka.rabbitmq.consumer.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//@Service
public class HelloRabbitConsumer {

    @RabbitListener(queues = "emeka.hello")
    public void listen(String message){
        System.out.println("CONSUMING MESSAGE " +message);
    }
}
