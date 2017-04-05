package com.aws.kinesis;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.kinesis.AmazonKinesisClient;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhjm on 2016/12/29.
 */
public class MyKinesisClient {

    private final String STREAM_NAME = "";

    @Autowired
    private AmazonKinesisClient client;

    private PutRecordRequest putRecordRequest;

    public MyKinesisClient(){
        /*client = new AmazonKinesisClient(new AWSCredentials() {
            public String getAWSAccessKeyId() {
                return "AKIAPFY2KPDNKK52Y3SA";
            }

            public String getAWSSecretKey() {
                return "s+q2tF2v53u+cubUGoq1tXMOCZ3JNupEOlk56erk";
            }
        });*/
    }
}
