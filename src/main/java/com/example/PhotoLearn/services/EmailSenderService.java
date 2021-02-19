package com.example.PhotoLearn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private JavaMailSender emailSender;

    public void send(String emailTo, String subject, String message) {
        SimpleMailMessage emailMessage = new SimpleMailMessage();

        emailMessage.setFrom(this.username);
        emailMessage.setTo(emailTo);
        emailMessage.setSubject(subject);
        emailMessage.setText(message);

        this.emailSender.send(emailMessage);
    }

}
