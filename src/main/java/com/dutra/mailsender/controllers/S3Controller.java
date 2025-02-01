package com.dutra.mailsender.controllers;

import com.dutra.mailsender.dtos.EmailDto;
import com.dutra.mailsender.dtos.S3Dto;
import com.dutra.mailsender.services.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/s3")
public class S3Controller {

    private final S3Service s3Service;
    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping
    public ResponseEntity<Void> sendMail(@RequestBody S3Dto s3Dto) {
        s3Service.uploadFile(s3Dto);
        return ResponseEntity.noContent().build();
    }
}
