package com.example.MailService.service.map;

import com.example.MailService.model.Mail;
import com.example.MailService.model.MailRequest;
import org.springframework.stereotype.Component;

@Component
public class MailMapper {

    public Mail maptoMailRequest(MailRequest request) {
        Mail mail = new Mail() ;
        mail.setMailTo(request.getMailTo()) ;
        mail.setMailContent(request.getMailContent());
        mail.setMailSubject(request.getMailSubject());
        mail.setMailFrom(request.getMailFrom());
        return mail ;

    }
}
