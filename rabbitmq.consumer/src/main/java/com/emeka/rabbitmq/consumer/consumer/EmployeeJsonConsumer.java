package com.emeka.rabbitmq.consumer.consumer;

import com.emeka.rabbitmq.consumer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * We set up the json consumer to be able to consume the employee object
 * To consume, the Json object is converted back to Employee Java object
 */
//@Service
public class EmployeeJsonConsumer {

    @Autowired
    private ObjectMapper objectMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeJsonConsumer.class);


    /**
     *The readValue() method from the mapper converts JSON string back to a Java Object
     */
    @RabbitListener(queues = "emeka.employee")
    public void listen (String message) throws JsonProcessingException {
        Employee employee = objectMapper.readValue(message, Employee.class);
        LOGGER.info("Employee from queue is {}", message);

    }
}
