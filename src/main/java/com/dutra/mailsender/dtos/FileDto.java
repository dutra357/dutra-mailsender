package com.dutra.mailsender.dtos;

import org.springframework.web.multipart.MultipartFile;

public class FileDto {

    private MultipartFile file;

    public FileDto() {}
    public FileDto(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
