package com.performance;

import com.alibaba.fastjson.JSON;
import com.amazonaws.services.autoscaling.model.Instance;
import com.amazonaws.util.json.Jackson;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.others.User;

/**
 * Created by zhjm on 2017/2/16.
 */
public class SwapTest {

    public static void main(String[] args) throws Exception{
        int a = 6;
        int b = 7;
        System.out.println(a ^ b ^ b);//a
        System.out.println(a ^ b ^ a);//b

        User user = new User("hello", 12);
        String s = JSON.toJSONString(user);
        System.out.println(JSON.toJSON(user));

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode node = mapper.readTree(s);
        System.out.println(node);
        System.out.println(node.asText());
        System.out.println(node.get("name"));
        String msg = node.get("name").asText();
        System.out.println(msg);
    }
}
