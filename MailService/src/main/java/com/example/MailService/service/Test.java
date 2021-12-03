package com.example.MailService.service;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Test {
    public static void main(String[] args) throws IOException, TimeoutException {

//        ConnectionFactory factory = new ConnectionFactory();
//
//        factory.setHost("localhost");
//        factory.setPort(5762);
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        channel.queueBind("javatechie_queue", "", "");
//
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            System.out.println(" [x] Received '" + message + "'");
//        };
//        channel.basicConsume("javatechie_queue", true, deliverCallback, consumerTag -> { });


//        System.out.println("Create a ConnectionFactory");
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("192.168.50.33");
//        factory.setPort(5673);
//        factory.setUsername("admin");
//        factory.setPassword("adminlgsp@123");
//
//        System.out.println("Create a Connection");
//        System.out.println("Create a Channel");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        System.out.println("Create a queue " + "cv.queue");
////        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//
//        System.out.println("Start receiving messages ... ");
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            System.out.println(" [x] Received: '" + message + "'");
//        };
//        CancelCallback cancelCallback = consumerTag -> {
//        };
//        String consumerTag = channel.basicConsume("test", true, deliverCallback, cancelCallback);
//        System.out.println("consumerTag: " + consumerTag);

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

//        System.out.println("Create a queue " + "javatechie_queue");
//        channel.queueDeclare("javatechie_queue", true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        System.out.println("Start receiving messages ... ");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received: '" + message + "'");
        };
        CancelCallback cancelCallback = consumerTag -> {
        };
        String consumerTag = channel.basicConsume("test", true, deliverCallback, cancelCallback);
        System.out.println("consumerTag: " + consumerTag);


    }

}
