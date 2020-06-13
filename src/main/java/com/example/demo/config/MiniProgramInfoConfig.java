package com.example.demo.config;

/**
 * @author sebastian
 * @date 2020/6/6 22:48
 */
public class MiniProgramInfoConfig {
    public static String getKEY() {
        return KEY;
    }

    public static String getAppId() {
        return APP_ID;
    }

    public static String getMchId() {
        return MCH_ID;
    }

    public static String getAppSecret() {
        return APP_SECRET;
    }

    private static final String KEY = "商户的api秘钥";

    private static final String APP_ID = "wxad52309b82fd0e93";

    private static final String MCH_ID = "商户号";

    private static final String APP_SECRET = "6034ff02ba2ea79c0ecd76c8d1e45a14";
}
