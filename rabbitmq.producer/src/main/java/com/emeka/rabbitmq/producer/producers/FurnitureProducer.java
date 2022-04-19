package com.emeka.rabbitmq.producer.producers;

import com.emeka.rabbitmq.producer.entity.Furniture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class FurnitureProducer {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    public  void sendMessage(Furniture furniture) throws JsonProcessingException {
        MessageProperties properties = new MessageProperties();
        properties.setHeader("color",furniture.getColor());
        properties.setHeader("material", furniture.getMaterial());
        String jsonFurniture = objectMapper.writeValueAsString(furniture);
      Message message =  new Message(jsonFurniture.getBytes(),properties);

      template.send("x.promotion","",message);
    }
}
