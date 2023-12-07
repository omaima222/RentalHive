package com.root.rentalheive.services;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class S3Service {

    private final AmazonS3 amazonS3;
   // @Value("${amazon.aws.accesskey}")
    private String awsAccessKey="";

   // @Value("${amazon.aws.secretkey}")
    private String awsSecretKey="";

  //  @Value("${amazon.aws.region}")
    private String awsRegion="";

  //  @Value("${aws.bucket.name}")
    private String bucketName="rentalhive";
    public S3Service() {

        BasicAWSCredentials credentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(awsRegion)
                .build();
    }

    public void uploadFile( String key, MultipartFile file) {
        try {
            long contentLength = file.getSize(); // The length of the stream data

            // Create the object metadata and set the content length
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(contentLength);
            amazonS3.putObject(new PutObjectRequest(bucketName, key, file.getInputStream(), null ));

        } catch (IOException e) {
            // Handle the exception
            e.printStackTrace();
        }
    }
}
