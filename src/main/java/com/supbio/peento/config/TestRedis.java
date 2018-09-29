package com.supbio.peento.config;

import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

public class TestRedis {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public static void main(String[] args) {

//        TestRedis testRedis = new TestRedis();
//        System.out.println("leiTest过期时间: " + testRedis.testSet("leiTest"));

        //实例化一个客户端
        Jedis jedis = new Jedis("localhost");
        //ping下，看看是否通的
        System.out.println("Server is running: " + jedis.ping());
//        jedis.flushDB();
        //保存一个
        jedis.set("liang1", "localhost Connection  sucessfully");
        System.out.println("liang1 过期时间设置: " + jedis.ttl("liang1"));
//        Long expire = jedis.expire("liang1", 30);
//        System.out.println("liang1 过期时间设置: " + expire);
        //获取一个
        String leite=jedis.get("liang1");
        System.out.println("liang1: " +leite);

//        TestRedis testRedis = new TestRedis();
//        String str = "leiTest";
//        String s = testRedis.testGet(str);
//        System.out.println("leiTest 值: " + s);
//        Long aLong = testRedis.testSet(str);
//        System.out.println("leiTest获取过期时间: " + aLong);


    }

}
