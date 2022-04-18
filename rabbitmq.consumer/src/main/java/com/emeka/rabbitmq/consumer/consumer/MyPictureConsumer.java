package com.emeka.rabbitmq.consumer.consumer;

import com.emeka.rabbitmq.consumer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MyPictureConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PictureConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    // Listener method that takes JSON message from RabbitMq
    //And converts it to picuter object with the mapper

    @RabbitListener(queues = "q.mypicture.image")

    public void listen(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG)long tag)
            throws IOException {
        Picture picture =  objectMapper.readValue(message, Picture.class);
        if(picture.getSize() > 9000){
        // Instead of throwing exception, in manual rejection use channel.basicReject
//            throw new AmqpRejectAndDontRequeueException("Picture size too large "+picture);
            channel.basicReject(tag,false);
            return;
        }
        LOGGER.info("On Image {}",picture );
        channel.basicAck(tag,false);
    }

}
