package com.naresh.springbootS3.controller;

import com.naresh.springbootS3.Service.S3Service;
import com.naresh.springbootS3.Service.impl.S3ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/s3")
@RestController
public class S3Controller {

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(S3ServiceImpl.class);

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service){
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadJsonToS3() {

        log.info("Received request to upload JSON file to S3.");

        s3Service.uploadJson();

        return new ResponseEntity<>(
                "JSON file uploaded successfully to S3 bucket.",
                HttpStatus.OK
        );
    }
}
