package com.unicom.urban.minio;

import cn.hutool.core.util.RandomUtil;
import io.minio.*;
import lombok.SneakyThrows;
import org.apache.commons.compress.utils.FileNameUtils;
import org.springframework.web.multipart.MultipartFile;

public class MinIOUploadImpl implements MinIOUpload {

    private MinioClient minioClient;

    private UrbanMinIOProperties urbanMinIOProperties;

    public MinIOUploadImpl(MinioClient minioClient, UrbanMinIOProperties urbanMinIOProperties) {
        this.minioClient = minioClient;
        this.urbanMinIOProperties = urbanMinIOProperties;
    }

    @Override
    @SneakyThrows
    public boolean bucketExists(String bucketName) {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    @Override
    @SneakyThrows
    public void makeBucket(String bucketName) {
        if (!bucketExists(bucketName)) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    @Override
    @SneakyThrows
    public String upload(MultipartFile file) {
        makeBucket(urbanMinIOProperties.getBucket());
        return minioClient.putObject(
                PutObjectArgs.builder()
                        .stream(file.getInputStream(), file.getSize(), 50000000)
                        .contentType(file.getContentType())
                        .object(rename(file.getOriginalFilename()))
                        .bucket(urbanMinIOProperties.getBucket())
                        .build()).object();
    }

    @Override
    @SneakyThrows
    public GetObjectResponse read(String fileName) {
        return minioClient.getObject(GetObjectArgs.builder().bucket(urbanMinIOProperties.getBucket()).object(fileName).build());
    }

    private String rename(String originalFilename) {
        return RandomUtil.randomString(32) + "." + FileNameUtils.getExtension(originalFilename);
    }

}
