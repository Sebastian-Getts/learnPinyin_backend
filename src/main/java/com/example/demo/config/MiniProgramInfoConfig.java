package com.example.demo.config;

/**
 * @author sebastian
 * @date 2020/6/6 22:48
 */
public class MiniProgramInfoConfig {
    public static String getKey() {
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

    private static final String APP_ID = "your app id";

    private static final String MCH_ID = "商户号";

    private static final String APP_SECRET = "your app secret";
}
