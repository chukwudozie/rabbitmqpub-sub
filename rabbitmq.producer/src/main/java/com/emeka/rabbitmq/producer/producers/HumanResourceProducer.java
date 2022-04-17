package com.emeka.rabbitmq.producer.producers;

import com.emeka.rabbitmq.producer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HumanResourceProducer {

    @Autowired
    private RabbitTemplate template;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     *This method converts an object of the employee class to JSON string\
     * Using the writeValueASString method of the ObjectMapper
     */
    public void  sendMessage(Employee employee) throws JsonProcessingException {
        String employeeJson  = objectMapper.writeValueAsString(employee);
//         the convertAndSend() method now takes 3 params: xchange name, the routing key and object to convert
//        For fan out exchange, the routing key can be any string since it sends message to all queues
        template.convertAndSend("x.hr","",employeeJson);
    }
}
