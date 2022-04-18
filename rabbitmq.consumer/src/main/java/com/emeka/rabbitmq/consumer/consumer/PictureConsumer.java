package com.emeka.rabbitmq.consumer.consumer;

import com.emeka.rabbitmq.consumer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class PictureConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PictureConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    // Listener method that takes JSON message from RabbitMq
    //And converts it to picuter object with the mapper

    @RabbitListener(queues = "q.picture.image")
    public void listen(String message) throws JsonProcessingException {
       Picture picture =  objectMapper.readValue(message, Picture.class);
        LOGGER.info("On Image {}",picture );
    }
    @RabbitListener(queues = "q.picture.vector")
    public  void listenForVector (String message) throws JsonProcessingException {
        Picture picture =  objectMapper.readValue(message, Picture.class);
        LOGGER.info("On Image {}",picture );
    }

}
