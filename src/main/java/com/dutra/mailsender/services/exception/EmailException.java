package com.dutra.mailsender.services.exception;

public class EmailException extends RuntimeException{
    public EmailException(String message) {
        super(message);
    }
}
