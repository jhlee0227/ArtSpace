package com.example.demo.file.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.demo.file.dto.GCSRequest;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class GCSService {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    public void uploadObject(GCSRequest gcsRequest) throws IOException {

        String keyFileName = "quiet-chalice-419309-a18ccc6da276.json";
        InputStream keyFile = ResourceUtils.getURL("classpath:" + keyFileName).openStream();

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(keyFile))
                .build()
                .getService();

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, gcsRequest.getFile().getOriginalFilename())
                .setContentType(gcsRequest.getFile().getContentType()).build();

        Blob blob = storage.create(blobInfo, gcsRequest.getFile().getInputStream());

    }

}
