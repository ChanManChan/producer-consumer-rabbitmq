package com.u4.rabbitmq.consumer.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.u4.rabbitmq.consumer.entity.Picture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;


//@Service
public class MyPictureImageConsumer {
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(MyPictureImageConsumer.class);

    // Solution one for DLX
//    @RabbitListener(queues = "q.mypicture.image")
//    public void listen(String message) {
//        try {
//            var p = objectMapper.readValue(message, Picture.class);
//
//            if(p.getSize() > 9000) {
//                throw new AmqpRejectAndDontRequeueException("Picture size too large: " +  p);
//            }
//
//            log.info("On image: {}", p.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    // Solution two for DLX
    @RabbitListener(queues = "q.mypicture.image")
    public void listen(Message message, Channel channel) throws IOException {
            var p = objectMapper.readValue(message.getBody(), Picture.class);

            if(p.getSize() > 9000) {
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            }
            log.info("On image: {}", p.toString());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
