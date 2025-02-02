package com.dutra.mailsender.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.dutra.mailsender.dtos.UriDto;
import org.apache.commons.io.FilenameUtils;
import org.joda.time.IllegalInstantException;
import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class S3Service {

    private static final Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;


    public UriDto sendFileToS3(MultipartFile file) {
        URL url = uploadFile(file);
        return new UriDto(url.toString());
    }

    private URL uploadFile(MultipartFile file) {
        try {
            LOG.info("Call upload (File).");

            String originalName = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalName);
            String fileName = Instant.now().toDate().getTime() + "." + extension;

            InputStream inputStream = file.getInputStream();
            String contentType = file.getContentType();

            LOG.info("Upload start");

            return uploadFileSender(inputStream, fileName, contentType);
        }
        catch (IOException e) {
            LOG.error("IllegalInstantException.");
            throw new IllegalInstantException(e.getMessage());
        }
    }

    private URL uploadFileSender(InputStream inputStream, String fileName, String contentType) {
        LOG.info("Upload start");

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);

        s3client.putObject(bucketName, fileName, inputStream, metadata);

        LOG.info("Upload end");
        return s3client.getUrl(bucketName, fileName);
    }
}
