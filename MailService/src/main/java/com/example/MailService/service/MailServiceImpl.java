package com.example.MailService.service;

import com.example.MailService.MailRepository.MailRepository;
import com.example.MailService.model.Mail;
import com.example.MailService.model.MailRequest;
import com.example.MailService.service.map.MailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender ;

    @Autowired
    MailRepository mailRepository ;

    @Autowired
    MailMapper mailMapper ;

    @Override
    public void sendEmail(MailRequest mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "doxuanlam0902@gmail.com"));
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent());

            mailSender.send(mimeMessageHelper.getMimeMessage());
            mailRepository.save(mailMapper.maptoMailRequest(mail)) ;


        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
