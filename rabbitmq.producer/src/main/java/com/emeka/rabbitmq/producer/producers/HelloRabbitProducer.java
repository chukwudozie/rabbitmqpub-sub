package com.emeka.rabbitmq.producer.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class HelloRabbitProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /*
     * This method is used to send a message to the Rabbit MQ
     * The convertAndSend() Method from RabbitTemplate class
     * converts the message  to byte before sending...
     * convertAndSend() method takes queue name and message as parameters
     */

    public void sendHello(String name){
        rabbitTemplate.convertAndSend("emeka.hello","Hello "+name);
    }
}
