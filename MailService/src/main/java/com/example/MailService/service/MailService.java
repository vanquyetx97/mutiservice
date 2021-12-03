package com.example.MailService.service;

import com.example.MailService.model.Mail;
import com.example.MailService.model.MailRequest;

public interface MailService {
    public void sendEmail(MailRequest mail) ;
}
