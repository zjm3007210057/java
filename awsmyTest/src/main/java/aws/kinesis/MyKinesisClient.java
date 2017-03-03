package aws.kinesis;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.AmazonKinesisClient;
import com.amazonaws.services.kinesis.model.*;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang.jianming on 2016/12/29.
 */
public class MyKinesisClient {

    @Autowired
    private AmazonKinesisClient client;

    @Autowired
    private AmazonS3Client s3Client;

    private AWSCredentials credentials;

    private final String STREAM_NAME = "dev-streamTest";

    private final String S3_BUCKET_NAME = "dev-kinesis-s3-bucket";

    private PutRecordRequest recordRequest;

    private GetRecordsRequest getRecordsRequest;

    private List<PutRecordsRequestEntry> entryList;

    private String shardIterator;

    public MyKinesisClient(){
        credentials = new AWSCredentials() {
            public String getAWSAccessKeyId() {
                return "AKIAPD7ON6HIY4KWXIQQ";
            }
            public String getAWSSecretKey() {
                return "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";
            }
        };
        client = new AmazonKinesisClient(credentials);
        s3Client = new AmazonS3Client(credentials);
        client.setRegion(Region.getRegion(Regions.CN_NORTH_1));
        s3Client.setRegion(Region.getRegion(Regions.CN_NORTH_1));
        recordRequest = new PutRecordRequest();
        recordRequest.setStreamName(STREAM_NAME);
        recordRequest.setPartitionKey("0");
        entryList = new ArrayList<PutRecordsRequestEntry>();
    }

    public void putStream(String fileName){
        File file = new File(fileName);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            FileChannel fin = inputStream.getChannel();
            byte[] bytes = new byte[1024];
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int num;
            while((num = fin.read(buffer)) != -1){
                buffer.flip();
                recordRequest.setData(buffer);
//                PutRecordsRequestEntry entry = new PutRecordsRequestEntry();
//                entry.setData(buffer);
//                entryList.add(entry);
                client.putRecord(recordRequest);
                buffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readStreamAndPutS3(){
        File file = this.readStream("20161230-test.txt");
        String key = "20161230-test.txt";
        s3Client.putObject(S3_BUCKET_NAME, key, file);
    }

    public File readStream(String fileName){
        File file = new File(fileName);
        FileOutputStream stream;
        GetShardIteratorRequest getShardIteratorRequest = new GetShardIteratorRequest();
        getShardIteratorRequest.setStreamName(STREAM_NAME);
        getShardIteratorRequest.setShardId("0");
        getShardIteratorRequest.setShardIteratorType("TRIM_HORIZON");

        GetShardIteratorResult getShardIteratorResult = client.getShardIterator(getShardIteratorRequest);
        shardIterator = getShardIteratorResult.getShardIterator();

        getRecordsRequest = new GetRecordsRequest();
        getRecordsRequest.setLimit(25);
        getRecordsRequest.setShardIterator(shardIterator);
        GetRecordsResult res = client.getRecords(getRecordsRequest);
        List<Record> list = res.getRecords();
        byte[] bytes;
        ByteBuffer buffer;
        for(Record record : list){
            buffer = record.getData();
            buffer.flip();
//            buffer.get(record.getData().array(), 0, record.getData().capacity());
            bytes = buffer.array();
            System.out.println(new String(bytes));
            try{
                stream = new FileOutputStream(file);
                stream.write(bytes);
            }catch(IOException e){
                e.printStackTrace();
            }
            buffer.clear();
        }
        return file;
    }

    public static void main(String[] args) {
        MyKinesisClient client = new MyKinesisClient();
        client.putStream("D:\\谷歌书签.html");
//        client.readStream("");
        client.readStreamAndPutS3();
    }

}
