package com.ouzili.jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @author ouzili
 * @create 2022-03-30 14:10
 */
public class PhoneCode {

    public static void main(String[] args) {

    }

    public static void verifyCode(String phone, String code) {
        Jedis jedis = new Jedis("192.168.198.129", 6379);
        String countKey = "VerifyCode" + phone + ":count";
        String codeKey = "VerifyCode" + phone + ":code";

        String count = jedis.get(countKey);
        if (count == null) {
            jedis.setex(countKey, 24*60*60, "1");
        } else if (Integer.parseInt(count) <= 2) {
            jedis.incr(countKey);
        } else if (Integer.parseInt(count) > 2) {
            System.out.println("发送超过三次");
            jedis.close();
        }

        String vcode = getCode();
        jedis.setex(codeKey, 120, vcode);
        jedis.close();
    }

    public static String getCode() {
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            code += rand;
        }
        return code;
    }
}
