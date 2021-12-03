package com.example.MailService.controller;

import com.example.MailService.Consume.ConsumerConfig;
import com.example.MailService.model.MailRequest;
import com.example.MailService.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@Slf4j
@RequestMapping("/api/v1")
@CrossOrigin
public class MailController {

    @Autowired
    JavaMailSender javaMailSender ;

    @Autowired
    MailService mailService ;

    public ConsumerConfig consumerConfig = new ConsumerConfig();

    @PostMapping(value = "/sendMail")
    public String sendMail(@RequestBody MailRequest mail) throws IOException, TimeoutException {

        // Create a Simple MailMessage.
        String text = consumerConfig.getMessage() ;
        System.out.println("MESSAGE"  + text );
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mail.getMailTo());
        message.setSubject("Test Simple Email");
        message.setText(text);

        // Send Message!
        javaMailSender.send(message);


        return "Email Sent!";

    }

}
