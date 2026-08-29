package com.digicart.store.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class GcsStorageService {

    @Value("${gcs.bucket:}")
    private String bucket;

    private Storage storage;

    @PostConstruct
    public void init() {
        if (bucket != null && !bucket.isBlank()) {
            this.storage = StorageOptions.getDefaultInstance().getService();
        }
    }

    public boolean isConfigured() {
        return storage != null;
    }

    public String upload(MultipartFile file) throws IOException {
        String original = file.getOriginalFilename() != null ? file.getOriginalFilename() : "upload";
        String name = "store-uploads/" + UUID.randomUUID() + "-" + original;
        BlobId blobId = BlobId.of(bucket, name);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType())
                .build();
        storage.create(blobInfo, file.getBytes());
        return "https://storage.googleapis.com/" + bucket + "/" + name;
    }
}
