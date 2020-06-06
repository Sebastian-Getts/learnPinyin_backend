package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author sebastian
 * @date 2020/5/23 11:42
 */
@Slf4j
public class CommonUtil {


    public static String getStringRandom(int length) {

        StringBuilder val = new StringBuilder();
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val.append((char) (random.nextInt(26) + temp));
            } else {
                val.append(random.nextInt(10));
            }
        }
        return val.toString();
    }

    public static void main(String[] args) {
        System.out.println(getStringRandom(16));
    }

}
