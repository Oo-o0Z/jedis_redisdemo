package com.ouzili.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author ouzili
 * @create 2022-03-29 18:18
 */
public class JedisDemo1 {

    @Test
    public void test1() {
        Jedis jedis = new Jedis("192.168.198.129", 6379);

        String response = jedis.ping();
        System.out.println(response);
    }

    @Test
    public void test2() {
        Jedis jedis = new Jedis("192.168.198.129", 6379);
        Set<String> keys = jedis.keys("*");
        keys.forEach(System.out::println);
        jedis.close();
    }
}
