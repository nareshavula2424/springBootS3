package com.naresh.springbootS3.Service.impl;

import com.naresh.springbootS3.Service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Files;

@Service
@Slf4j
public class S3ServiceImpl implements S3Service {

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(S3ServiceImpl.class);

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    private final S3Client s3Client;

    public S3ServiceImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public void uploadJson() {

        try {

            log.info("Reading JSON file...");

            ClassPathResource resource =
                    new ClassPathResource("data/employee.json");

            byte[] fileBytes =
                    Files.readAllBytes(resource.getFile().toPath());

            PutObjectRequest request =
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key("employee.json")
                            .contentType("application/json")
                            .build();

            s3Client.putObject(
                    request,
                    RequestBody.fromBytes(fileBytes)
            );

            log.info("JSON uploaded successfully.");

        }
        catch (Exception ex) {

            log.error("Upload failed", ex);

            throw new RuntimeException("Failed to upload JSON file to S3", ex);

        }

    }
}
