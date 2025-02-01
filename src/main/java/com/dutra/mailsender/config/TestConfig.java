package com.dutra.mailsender.config;

import com.dutra.mailsender.services.MockEmailService;
import com.dutra.mailsender.services.interfaces.EmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
