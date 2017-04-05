package com.me.http;

import com.alibaba.fastjson.JSONObject;
import com.me.collection.CollectionUtil;
import com.me.object.ObjectUtil;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * 发送http请求类
 * Created by zjm on 2017/4/5.
 */
public class HttpRequestUtils {

    /**
     * sendHttpPost
     * @param url
     * @param jsonObject
     * @return
     */
    public static JSONObject sendHttpPost(String url, JSONObject jsonObject){
        return sendHttpPost(url, jsonObject, null);
    }

    /**
     * sendHttpPost
     * @param url
     * @param jsonObject
     * @return
     */
    public static JSONObject sendHttpPost(String url, JSONObject jsonObject, Header header){
        return sendHttpPost(url, jsonObject, header, false);
    }

    /**
     * 发送post请求
     * @param url
     * @param jsonObject
     * @param noNeedResponse
     * @return
     */
    public static JSONObject sendHttpPost(String url, JSONObject jsonObject, Header header, boolean noNeedResponse){
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        JSONObject jsonResult = null;
        try{
           if(ObjectUtil.isNotNull(jsonObject)){
                StringEntity entity = new StringEntity(jsonObject.toJSONString(), "utf-8");
                entity.setContentEncoding("utf-8");
                entity.setContentType("application/json");
                post.setEntity(entity);
           }
           HttpResponse response = client.execute(post);
           if(ObjectUtil.isNotNull(header)){
               post.setHeader(header);
           }
           /**请求发送成功，并得到响应**/
           if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
               String str;
               str = EntityUtils.toString(response.getEntity());
               if(noNeedResponse){
                   return null;
               }
               jsonResult = JSONObject.parseObject(str);
           }else{
               jsonResult.put("http返回状态码", response.getStatusLine().getStatusCode());
               jsonResult.put("response结果", EntityUtils.toString(response.getEntity()));
           }
        }catch(IOException e){
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * sendHttpGet
     * @param url
     * @return
     */
    public static JSONObject sendHttpGet(String url){
        return sendHttpGet(url, null, null);
    }

    /**
     * 发送get请求
     * @param url
     * @param header
     * @param paramsMap
     * @return
     */
    public static JSONObject sendHttpGet(String url, Header header, Map<String, Object> paramsMap){
        JSONObject result = null;
        try{
            HttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet();
            if(CollectionUtil.isNotEmpty(paramsMap)){
                StringBuffer buffer = new StringBuffer(url + "?");
                paramsMap.forEach((k, v) -> {
                    buffer.append(k);
                    buffer.append("=");
                    buffer.append(v);
                    buffer.append("&");
                });
                url = buffer.toString();
                url = url.substring(0, url.length() - 1);
            }
            request.setURI(URI.create(url));
            if(ObjectUtil.isNotNull(header)){
                request.setHeader(header);
            }
            HttpResponse response = client.execute(request);
            /**请求发送成功，并得到响应**/
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String str = EntityUtils.toString(response.getEntity());
                result = JSONObject.parseObject(str);
            }else{
               result.put("http返回状态码", response.getStatusLine().getStatusCode());
               result.put("response结果", EntityUtils.toString(response.getEntity()));
           }
        }catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }

}
