package com.unicom.urban.minio;

import io.minio.GetObjectResponse;
import io.minio.ObjectWriteResponse;
import org.springframework.web.multipart.MultipartFile;

public interface MinIOUpload {

    boolean bucketExists(String bucketName);

    void makeBucket(String bucketName);

    @Deprecated
    String upload(MultipartFile file);

    ObjectWriteResponse upload(String bucketName, MultipartFile file);

    ObjectWriteResponse update(String bucketName, String fileName, MultipartFile file);

    @Deprecated
    GetObjectResponse read(String fileName);

    GetObjectResponse read(String bucketName, String fileName);

}
