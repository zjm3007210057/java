package aws.s3;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.bestv.message.receive.MessageServiceCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zjm on 2016/12/6.
 */
public class S3Simple extends MessageServiceCallback {

    private AmazonS3Client client;

    private final Pattern MATCH_FILE_PATH;

    private final String bucketName;

    public S3Simple(){
        client = new AmazonS3Client(new AWSCredentials() {
            public String getAWSAccessKeyId() {
                return "AKIAO7WM5Q2RNP5GPH4Q";
            }
            public String getAWSSecretKey() {
                return "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";
            }
        });
        client.setRegion(Region.getRegion(Regions.CN_NORTH_1));
        MATCH_FILE_PATH = Pattern.compile("(\\d{8})(\\w+)(\\..*?)");
        bucketName = "for-asia-demo";
    }

    @Override
    public void doAction(String msg) throws Exception {
        JSONObject jsonObject = JSON.parseObject(msg);
        JSONArray jsonArray = jsonObject.getJSONArray("Records");
        for(int i=0; i<jsonArray.size(); i++){
            JSONObject object = jsonArray.getJSONObject(i);
            String key = object.getJSONObject("s3").getJSONObject("object").getString("key");
            Matcher matcher = MATCH_FILE_PATH.matcher(key);
            if(matcher.matches()){
                String fileName = matcher.group(0);
                System.out.println("fileName : " + fileName);
                String fileDateString = matcher.group(1);
                System.out.println("fileDateString" + fileDateString);
                S3Object s3Object = client.getObject(bucketName, key);
                S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
                byte[] bytes = new byte[1024];
                File newFile = new File(key);
                FileOutputStream os = new FileOutputStream(newFile);
                int num;
                while((num = s3ObjectInputStream.read(bytes)) != -1){
                    os.write(bytes, 0, num);
                }
                os.close();
                client.putObject(bucketName, fileDateString + "/" + matcher.group(2), newFile);
            }
        }
    }
}
