package com.daniel.delivery.main.storage.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import com.daniel.delivery.abstraction.service.file.StorageService;

public class S3StorageService implements StorageService {
    private final String clientRegion;
    private final String bucketName;
    private final AmazonS3 s3Client;

    public S3StorageService(String clientRegion, String bucketName, AmazonS3 s3Client) {
        this.clientRegion = clientRegion;
        this.bucketName = bucketName;
        this.s3Client = s3Client;
    }

    @Override
    public String save(String fileName, InputStream inputStream) throws IOException {
        try {
            String fileKey = UUID.randomUUID().toString() + "-" + fileName;

            ObjectMetadata metadata = new ObjectMetadata();
            PutObjectRequest request = new PutObjectRequest(bucketName, fileKey, inputStream, metadata);

            s3Client.putObject(request);

            return fileKey;
        } catch (AmazonServiceException e) {
            throw new IOException(e);
        } catch (SdkClientException e) {
            throw new IOException(e);
        }
    }

    @Override
    public InputStream load(String fileName) throws IOException {
        GetObjectRequest request = new GetObjectRequest(bucketName, fileName);

        S3Object object = s3Client.getObject(request);

        return object.getObjectContent();
    }

}
