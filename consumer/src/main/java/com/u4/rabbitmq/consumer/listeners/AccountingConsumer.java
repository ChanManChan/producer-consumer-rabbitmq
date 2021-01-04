package com.u4.rabbitmq.consumer.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.u4.rabbitmq.consumer.entity.Employee;
import com.u4.rabbitmq.consumer.listeners.EmployeeJsonConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;

//@Service
public class AccountingConsumer {
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(EmployeeJsonConsumer.class);

    @RabbitListener(queues = "q.hr.accounting")
    public void listen(String message) {
        Employee emp = null;
        try {
            emp = objectMapper.readValue(message, Employee.class);
            log.info("On accounting, employee is {}", emp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
