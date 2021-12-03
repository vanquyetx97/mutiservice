package com.example.MailService.Consume;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public class ConsumerConfig {
    public static String a = null;
    public String getMessage() throws IOException, TimeoutException {
        System.out.println("Create a ConnectionFactory");
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        System.out.println("Create a Connection");
        System.out.println("Create a Channel");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        System.out.println("Start receiving messages ... ");
        Object res = new Object();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received: '" + message + "'");
            a = message;

        };



        CancelCallback cancelCallback = consumerTag -> {
        };
        String consumerTag = channel.basicConsume("message_queue", true, deliverCallback, cancelCallback);
        System.out.println("consumerTag: " + consumerTag);

        System.out.println("----->mmmmm " + a);
        while (a==null)
            continue;
        return a ;

    }
}
