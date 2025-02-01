package com.dutra.mailsender.config;

import com.dutra.mailsender.services.SendGridEmailService;
import com.dutra.mailsender.services.interfaces.EmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public EmailService emailService() {
        return new SendGridEmailService();
    }
}
