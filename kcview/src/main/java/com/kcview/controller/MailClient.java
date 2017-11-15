package com.kcview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.kcview.entity.SuiviComptant;

@Service
public class MailClient {

    private JavaMailSender mailSender;
    private MailContentBuilder mailContentBuilder;

    @Autowired
    public MailClient(JavaMailSender mailSender, MailContentBuilder mailContentBuilder) {
        this.mailSender = mailSender;
        this.mailContentBuilder = mailContentBuilder;
    }

    public void prepareAndSend(String recipient, String message, List<SuiviComptant> suivis) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("kcview.mail@gmail.com");
            messageHelper.setTo(recipient);
            messageHelper.setSubject("[Importation] suivi comptant de " + message);
            //messageHelper.setText("ererer");
            String content = mailContentBuilder.build(message, suivis);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        	System.out.println("dddd");
        }
    }

}