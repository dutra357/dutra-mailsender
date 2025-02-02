package com.dutra.mailsender.controllers;

import com.dutra.mailsender.dtos.FileDto;
import com.dutra.mailsender.dtos.S3Dto;
import com.dutra.mailsender.dtos.UriDto;
import com.dutra.mailsender.services.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/s3")
public class S3Controller {

    private final S3Service s3Service;
    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping(value = "/file")
    public ResponseEntity<UriDto> sendFileToS3(@RequestParam("file") MultipartFile file) {
        UriDto uri = s3Service.sendFileToS3(file);
        return ResponseEntity.ok(uri);
    }
}
