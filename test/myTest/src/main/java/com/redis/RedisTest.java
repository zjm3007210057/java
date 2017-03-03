package com.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by zhjm on 2017/1/3.
 */
public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
//        jedis.sadd("test", "a");
//        jedis.sadd("test", "b");
        jedis.set("key", "value");
        System.out.println(jedis.get("key"));
        jedis.del("key");
        System.out.println(jedis.smembers("test"));
//        jedis.
    }
}
