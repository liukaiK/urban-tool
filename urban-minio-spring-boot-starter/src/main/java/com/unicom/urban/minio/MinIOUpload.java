package com.unicom.urban.minio;

import io.minio.GetObjectResponse;
import org.springframework.web.multipart.MultipartFile;

public interface MinIOUpload {

    boolean bucketExists(String bucketName);

    void makeBucket(String bucketName);

    String upload(MultipartFile file);

    GetObjectResponse read(String fileName);

}
