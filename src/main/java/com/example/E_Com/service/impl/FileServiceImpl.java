package com.example.E_com.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import com.example.E_com.service.FileService;
import com.example.E_com.util.CommonFileSavedBinaryDataDto;
import com.example.E_com.util.ImageUploadGenerator;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final S3Client s3Client;
    private final ImageUploadGenerator imageUploadGenerator;

    @Override
    public CommonFileSavedBinaryDataDto create(MultipartFile file, String directory, String bucket) {
        try {
            String originalFilename = file.getOriginalFilename();
            String newFileName = imageUploadGenerator.generateDevelopersStackResourceName(originalFilename, UUID.randomUUID().toString());

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(directory + newFileName)
                    .acl(ObjectCannedACL.PUBLIC_READ)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            return new CommonFileSavedBinaryDataDto(
                    new SerialBlob(newFileName.getBytes()),
                    directory,
                    new SerialBlob(newFileName.getBytes()),
                    new SerialBlob(s3Client.utilities().getUrl(builder -> builder.bucket(bucket).key(directory + newFileName)).toString().getBytes())
            );

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String fileName, String directory, String bucket) {
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(directory + fileName)
                .build());
    }

    @Override
    public byte[] download(String bucket, String fileName) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .build();

        return s3Client.getObject(getObjectRequest, ResponseTransformer.toBytes()).asByteArray();
    }
}
