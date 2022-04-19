package com.emeka.rabbitmq.producer.producers;

import com.emeka.rabbitmq.producer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class PictureProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage (Picture picture) throws JsonProcessingException {
        // convert Picture to JSON  String using object Mapper
        String pictureString  = objectMapper.writeValueAsString(picture);
        // params - exchange name, routing key, String of message
        rabbitTemplate.convertAndSend("x.picture",picture.getType(),pictureString);
    }
}
