package com.u4.rabbitmq.producer.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.u4.rabbitmq.producer.entity.Picture;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class MyPictureProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Picture p) {
        try {
            var json = objectMapper.writeValueAsString(p);
            rabbitTemplate.convertAndSend("x.mypicture", "", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
