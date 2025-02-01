package com.vinicius_lima.comanda_flash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.vinicius_lima.comanda_flash.services.exceptions.EmailException;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String emailFrom;


    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            emailSender.send(message);
            System.out.println("E-mail enviado com sucesso!");
        } catch (MailException e) {
            e.printStackTrace();
            throw new EmailException("Failed to send email: " + e.getMessage());
        }
    }
}

