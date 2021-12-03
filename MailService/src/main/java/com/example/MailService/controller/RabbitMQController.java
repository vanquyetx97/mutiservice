package com.example.MailService.controller;


import com.example.MailService.model.MailRequest;
import com.example.MailService.model.TestEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rabbit")
public class RabbitMQController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/send")
    public String sendRabbitMQ(@RequestParam("exchangeName") String exchange, @RequestParam("routingKey") String routingKey,
                           @RequestParam("messageData") String messageData) {
        rabbitTemplate.convertAndSend(exchange, routingKey, messageData);
        return "Message sent to the RabbitMq Successfully";
    }
}