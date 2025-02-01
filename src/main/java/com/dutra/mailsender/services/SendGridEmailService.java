package com.dutra.mailsender.services;

import com.dutra.mailsender.dtos.EmailDto;
import com.dutra.mailsender.services.exception.EmailException;
import com.dutra.mailsender.services.interfaces.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendGridEmailService implements EmailService {
    @Autowired
    private SendGrid sendGrid;

    @Override
    public void sendEmail(EmailDto emailDto) {
        Email from = new Email(emailDto.getFromEmail(), emailDto.getFromName());
        Email to = new Email(emailDto.getTo());
        Content content = new Content(emailDto.getContentType(), emailDto.getBody());

        Mail mail = new Mail(from, emailDto.getSubject(), to, content);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());


            System.out.println("Sending e-mail to: " + emailDto.getTo());

            Response response = sendGrid.api(request);

            if (response.getStatusCode() > 400 && response.getStatusCode() < 500) {
                System.out.println("Error sending e-mail: " + response.getBody());
                throw new EmailException(response.getBody());

            }

            System.out.println("Email sended! Status: " + response.getStatusCode());

        } catch (IOException e) {
            throw new EmailException(e.getMessage());
        }
    }
}
