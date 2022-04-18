package com.emeka.rabbitmq.producer.producers;

import com.emeka.rabbitmq.producer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// For topic exchange, we generate routing key that handles more than one criteria
public class PictureProducerForTopic {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage (Picture picture) throws JsonProcessingException {
        // convert Picture to JSON  String using object Mapper
        String pictureString  = objectMapper.writeValueAsString(picture);

        // Building the complex routing 3 parameter key: source.size.type
        StringBuilder routingKey = new StringBuilder();
        // for 1st word: picture source
        routingKey.append(picture.getSource());
        routingKey.append(".");
        //For  2nd word, size, only picture greater than 4,000 are large
        if(picture.getSize() > 4_000){
            routingKey.append("large");
        } else {
            routingKey.append("small");
        }
        routingKey.append(".");
        // for 3rd word: picture type
        routingKey.append(picture.getType());

        // params - exchange name, routing key, String of message
        rabbitTemplate.convertAndSend("x.picture2",routingKey.toString(),pictureString);
    }
}
