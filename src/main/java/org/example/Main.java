package org.example;

import io.minio.MinioClient;

public class Main {
    static String bucket = "";

    static MinioClient minioClient = MinioClient.builder()
            .endpoint("")
            .credentials("", "")
            .build();

    public static void main(String[] args) throws Exception{
        System.out.println(minioClient.listBuckets());
    }
}
