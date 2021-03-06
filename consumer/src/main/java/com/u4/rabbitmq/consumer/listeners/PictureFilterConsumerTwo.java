package com.u4.rabbitmq.consumer.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.u4.rabbitmq.consumer.entity.Picture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;


//@Service
public class PictureFilterConsumerTwo {
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(PictureFilterConsumerTwo.class);

    @RabbitListener(queues = "q.picture.filter")
    public void listen(String message) {
        try {
            var p = objectMapper.readValue(message, Picture.class);
            log.info("On filter: {}", p.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
