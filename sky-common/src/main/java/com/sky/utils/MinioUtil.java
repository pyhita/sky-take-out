package com.sky.utils;

import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: kante_yang
 * @Date: 2023/10/31
 */
public class MinioUtil {

    public static void main(String[] args)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://localhost:9000")
                            .credentials("ivxfBc51EhslF9qn4z7Y", "o6jFWTjzaqu849lPGHtk2exhgs0idxNCJpvvk9zW")
                            .build();

            // Make 'asiatrip' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("asiatrip").build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("asiatrip").build());
            } else {
                System.out.println("Bucket 'asiatrip' already exists.");
            }

            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            // 'asiatrip'.
//            minioClient.uploadObject(
//                    UploadObjectArgs.builder()
//                            .bucket("asiatrip")
//                            .object("asiaphotos-2023.png")
//                            .filename("/Users/kante_yang/Desktop/Info.png")
//                            .build());
            InputStream inputStream = new FileInputStream(new File("/Users/kante_yang/Desktop/Info.png"));
//            minioClient.putObject(
//                    PutObjectArgs.builder().bucket("asiatrip")
//                                    .object("asiaphotos3-2023.png")
//                                    .stream(inputStream, -1, 10485760)
//                            .contentType("picture/png")
//                            .build()
//            );
            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket("asiatrip")
                            .object("asiaphotos2-2023.png")
                            .expiry(1, TimeUnit.DAYS)
                            .build());
            System.out.println("url: " + url);
            System.out.println(
                    "'/home/user/Photos/asiaphotos.zip' is successfully uploaded as "
                            + "object 'asiaphotos-2015.zip' to bucket 'asiatrip'.");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
