package com.dutra.mailsender.services.interfaces;

import com.dutra.mailsender.dtos.EmailDto;

public interface EmailService {

    void sendEmail(EmailDto emailDto);
}
