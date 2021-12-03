package com.example.MailService.model;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Data
@Table(name = "mail")

public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String mailFrom;

    private String mailTo;

    private String mailSubject;

    private String mailContent;

}