package com.tang.wx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class WxApplicationTests {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testRedisBase() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        // 赋值取值
        valueOperations.set("token", "123456", 30, TimeUnit.SECONDS);
        String token = valueOperations.get("token");
        System.out.println("token = " + token);
        // 设置时间
        redisTemplate.expire("token", 300, TimeUnit.SECONDS);
        long timeout = redisTemplate.getExpire("token");
        System.out.println("timeout = " + timeout);
    }

    // redis List数据类型处理
    @Test
    void testRedisList() {
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        // 头部推入
        long a = listOperations.leftPush("a", "1");
        long b = listOperations.leftPush("a", "2");
        long c = listOperations.leftPush("a", "3");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        long size = listOperations.size("a");
        System.out.println("size =" + size);
        listOperations.rightPushAll("a", "x", "y", "z");
        listOperations.leftPushAll("a", new String[] {"hello", "world"});
        List<String> list = listOperations.range("a", 0, -1);
        for (String item: list) {
            System.out.println("item = " + item);
        }
    }

    // redis Hash数据类型测试
    @Test
    void testRedisHash() {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
//        hashOperations.put("map", "name", "twb");
//        Map<String, String> map = new HashMap<>();
//        map.put("age", "30");
//        map.put("hobby", "game");
//        hashOperations.putAll("map", map);
//        long size = hashOperations.size("map");
//        System.out.println("size = " + size);
//        String name = hashOperations.get("map", "name");
//        System.out.println("name = " + name);
        Set<String> keys = hashOperations.keys("map");
        for (String key: keys) {
            System.out.println("value = " + hashOperations.get("map", key));
        }
    }

}
