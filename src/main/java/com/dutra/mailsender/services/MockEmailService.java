package com.dutra.mailsender.services;


import com.dutra.mailsender.dtos.EmailDto;
import com.dutra.mailsender.services.interfaces.EmailService;

public class MockEmailService implements EmailService {
    @Override
    public void sendEmail(EmailDto emailDto) {
        System.out.println("E-mail enviado!");
    }
}
