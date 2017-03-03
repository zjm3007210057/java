package aws.s3;

import java.util.regex.Pattern;

/**
 * Created by zjm on 2016/12/7.
 */
public class Test {

    public static final Pattern MATCH_FILE_PATH = Pattern.compile("(\\d{8})(\\w+)\\..*?");

    public static void main(String[] args) {
        String str = "abc123";
        String s = "gf";
        String as = s.concat(str);
        System.out.println(as);
       /* String msg = "{\n" +
                "\t\"Records\":[{\"eventVersion\":\"2.0\",\"eventSource\":\"aws:s3\",\"awsRegion\":\"cn-north-1\",\"eventTime\":\"2016-12-06T08:53:19.701Z\",\"eventName\":\"ObjectCreated:Put\",\"userIdentity\":{\"principalId\":\"AWS:AIDAPPITBZIDVSDO67EG2\"},\"requestParameters\":{\"sourceIPAddress\":\"54.222.13.1\"},\"responseElements\":{\"x-amz-request-id\":\"C8E5763E0465FF37\",\"x-amz-id-2\":\"9dc3tY22J6M1E/qbU1vMR7b+VnP17RTPD9Hk301pF4D4AF0+vBcIB1eSOwWAIRuuSuw7ExQ2F6Y=\"},\"s3\":{\"s3SchemaVersion\":\"1.0\",\"configurationId\":\"for-asia-s3-process\",\"bucket\":{\"name\":\"for-asia-demo\",\"ownerIdentity\":{\"principalId\":\"AWS:304602643375\"},\"arn\":\"arn:aws:s3:::for-asia-demo\"},\"object\":{\"key\":\"20160423152757485.jpg\",\"size\":101333,\"eTag\":\"4d42370cec648cf4657a4fdcf0ddfa92\",\"sequencer\":\"0058467C7FA5EA7312\"}}}]\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(msg);
        JSONArray jsonArray = jsonObject.getJSONArray("Records");
        for(int i=0; i<jsonArray.size(); i++){
            JSONObject object = jsonArray.getJSONObject(i);
            String key1 = object.getJSONObject("s3").getJSONObject("object").getString("key");
            System.out.println(object);
            System.out.println("key1 : " + key1);
        }*/
    }
}
