package com.emeka.rabbitmq.consumer.consumer;

import com.emeka.rabbitmq.consumer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class PictureConsumerForTopic {
    private static final Logger LOGGER = LoggerFactory.getLogger(PictureConsumerForTopic.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = {"q.picture.image", "q.picture.vector", "q.picture.log", "q.picture.filter"})
    public void listen(Message messageAmqp) throws JsonProcessingException {
        String message = new String(messageAmqp.getBody());
        Picture picture = objectMapper.readValue(message, Picture.class);
        LOGGER.info("Image : {} with routing key : {} ",
                picture,messageAmqp.getMessageProperties().getReceivedRoutingKey());
    }


}
