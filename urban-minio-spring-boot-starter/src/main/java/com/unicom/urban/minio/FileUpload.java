package com.unicom.urban.minio;

public interface FileUpload {

    boolean bucketExists(String bucketName);

    void makeBucket(String bucketName);



}
