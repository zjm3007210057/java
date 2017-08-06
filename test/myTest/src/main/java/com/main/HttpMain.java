package com.main;


import com.alibaba.fastjson.JSONObject;
import com.me.collection.CollectionUtil;
import com.me.date.DateUtils;
import com.me.encrypt.EncryptUtil;
import com.me.http.HttpRequestUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

/**
 * Created by zjm on 2017/4/5.
 */
public class HttpMain {

    private final static String buCode = "1001";
    private final static String apiVersion = "v1";
    private final static String contentType = "application/json";
    private final static String key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApwbuhLHGlRmJwFwnH07ubOMZ3BFuEnAi" +
            "q+lRYGMZ5KznAO9p+ReEYU+smh/2FH94RRIDD0qi7Y29DfW0eUbhGiW7YV1dPtQ6obEeqBno2ZKN" +
            "SKiwNfyq+1PQuZBtmv0PuJlPTdlWEBSVkC3F3Twhaqgvxpv9Sy8FotZr4Sd4KYTNaW+YKOF0R5ph" +
            "sOEU2lhnc1gSr2jBdXg91eYp70/nEhS3lFEPZNO7d3XObLfMVirJnrTUQKE3RBUB4OqYVJscTMaz" +
            "Gy8wRz59ag9qPCHMhsF4iy+RmnfuLL7Ib94W1eQciQYMLLZHclnlGoiF8qDH0V8I8C/JZag9Skey" +
            "Iav45wIDAQAB";
    private final static String phoneNo = "18521701268";
    private final static String postUrl = "http://127.0.0.1:9013/passport/codes/phone-existence";
    private final static String getUrl = "http://10.205.35.75:9013/passport/oauth/url";
    private final static String getUrl1 = "http://localhost:9013/sms/reports";
    /**
     * 获取时间戳
     * @return
     */
    public static String getTimeStr(){
        return DateUtils.convertToStr(System.currentTimeMillis(), DateUtils.DATE_TYPE_YYYYMMDDHHMMSS);
    }

    /**
     * 获取SHA-256加密验签字符串
     * @return
     */
    public static String getSign(){
        String sourceStr = getTimeStr() + buCode + key;
        return EncryptUtil.SHA256Bytes2Hex(sourceStr);
    }

    /**
     * 发送get请求
     * @return
     */
    public static String sendHttpGet(){
        Header h1 = new BasicHeader("buCode", buCode);
        Header h2 = new BasicHeader("apiVersion", apiVersion);
        Header h3 = new BasicHeader("Content-Type", contentType);
        Header h4 = new BasicHeader("timeStr", getTimeStr());
        Header h5 = new BasicHeader("sign", getSign());
        Header[] headers = new Header[]{h1, h2, h3, h4, h5};
        Map map = new HashMap(2);
        map.put("oauthType", 1);
        JSONObject result = HttpRequestUtils.sendHttpGet(getUrl, headers, map);
        return result.toJSONString();
    }

    /**
     * 发送post请求
     * @return
     */
    public static String sendHttpPost(){
        Header h1 = new BasicHeader("buCode", buCode);
        Header h2 = new BasicHeader("apiVersion", apiVersion);
        Header h3 = new BasicHeader("Content-Type", contentType);
        Header h4 = new BasicHeader("timeStr", getTimeStr());
        Header h5 = new BasicHeader("sign", getSign());
        Header[] headers = new Header[]{h1, h2, h3, h4, h5};

        JSONObject jsonObject = new JSONObject();
        PhoneEntity data = new PhoneEntity(phoneNo);
        jsonObject.put("data", data);
        System.out.println(jsonObject.toJSONString());

        JSONObject result = HttpRequestUtils.sendHttpPost(postUrl, jsonObject, headers);

        return result.toJSONString();
    }

    /**
     * 手机号封装内部类
     * 目的是为了让--JSONObject 可以将其转换为json字符串，因为直接使用String字符串，在用JSONObject
     * 转成 json 字符串时会在大括号外边加上一层双引号，造成错误
     * example--原json串：
     *  {
     *      "data" : {
     *          "phoneNo" : "123******"
     *      }
     *  }
     *  如果直接使用String字符串，则经过JSONObejct转成json时变为：
     *  {
     *      "data" : "{
     *          "phoneNo" : "123******"
     *      }"
     *  }
     *  即在phoneNo前面一层大括号外会加上一层双引号，造成服务端解析错误
     */
    final static class PhoneEntity{

        private String phoneNo;

        public PhoneEntity(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }
    }

    /**
     * 发送get请求
     * @param url
     * @param params 请求参数
     * @return
     */
    public static String sendGet(String url, Map<String, Object> params) {
        String result = "";
        BufferedReader in = null;
        StringBuffer bf = new StringBuffer(url);
        String urlNameString;
        try {
            urlNameString = bf.toString();
            if(CollectionUtil.isNotEmpty(params)){
                bf.append("?");
                for(Entry entry : params.entrySet()){
                    bf.append(entry.getKey() + "=" + entry.getValue() + "&");
                }
                urlNameString = bf.toString().substring(0, bf.toString().length() - 1);
            }
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestMethod("GET");
            connection.setRequestProperty("buCode", buCode);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", contentType);
            connection.setRequestProperty("apiVersion", apiVersion);
            connection.setRequestProperty("timeStr", getTimeStr());
            connection.setRequestProperty("sign", getSign());
//            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {

        Map map = new HashMap(2);
        map.put("oauthType", 1);
        for (int i = 0; i < 1; i++) {
            System.out.println("时间戳为：" + getTimeStr() + " ||  验签为：" + getSign());
//            System.out.println(sendGet(getUrl, map));
//            System.out.println(sendHttpGet());
            System.out.println(sendHttpPost());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //"http://127.0.0.1:9013/passport/codes/phone-existence"
        String sss = "wddsds7676876asd87687asdasdq3424sdsd";
        Pattern pattern = Pattern.compile(".*");
        Pattern pattern1 = Pattern.compile("\\w{1,100}");
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            if(pattern.matcher(sss).matches()){
//                System.out.println("enenene");
            }
        }
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            if(pattern1.matcher(sss).matches()){
//                System.out.println("hahahah");
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
