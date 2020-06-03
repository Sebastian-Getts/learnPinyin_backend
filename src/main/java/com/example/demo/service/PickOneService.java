package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entities.CommonResult;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author sebastian
 * @date 2020/5/10 21:04
 */
@Service
@Slf4j
public class PickOneService {

    private final static String[] R_BASE = {"0", "1", "2", "3", "4", "5", "6", "7", "8",
            "9", "a", "b", "c", "d", "e", "f"};

    public static CommonResult<JSON> invoke(int count) {

        JSONObject object = new JSONObject();
        Random random = new Random();
        List<Map<String, Object>> wordList = new ArrayList<>();
        CommonResult<JSON> commonResult = new CommonResult<>();

        for (int i = 0; i < count; i++) {
            // 生成第1位的区码，11到14之间的随机数
            int r1 = random.nextInt(3) + 11;
            String strR1 = R_BASE[r1];
            // 生成第2位的区码
            int r2;
            if (r1 == 13) {
                //生成0到7之间的随机数
                r2 = random.nextInt(7);
            } else {
                //生成0到16之间的随机数
                r2 = random.nextInt(16);
            }
            String strR2 = R_BASE[r2];
            // 生成第1位的位码，10到16之间的随机数
            int r3 = random.nextInt(6) + 10;
            String strR3 = R_BASE[r3];
            // 生成第2位的位码
            int r4;
            if (r3 == 10) {
                //生成1到16之间的随机数
                r4 = random.nextInt(15) + 1;
            } else if (r3 == 15) {
                //生成0到15之间的随机数
                r4 = random.nextInt(15);
            } else {
                //生成0到16之间的随机数
                r4 = random.nextInt(16);
            }
            String strR4 = R_BASE[r4];
            log.info("区码+位码=" + strR1 + strR2 + strR3 + strR4);

            // 将生成机内码转换为汉字
            byte[] bytes = new byte[2];
            //将生成的区码保存到字节数组的第1个元素中
            String strR12 = strR1 + strR2;
            int tempLow = Integer.parseInt(strR12, 16);
            bytes[0] = (byte) tempLow;
            //将生成的位码保存到字节数组的第2个元素中
            String strR34 = strR3 + strR4;
            int tempHigh = Integer.parseInt(strR34, 16);
            bytes[1] = (byte) tempHigh;
            //根据字节数组生成汉字
            String word;
            String phonetic = "";
            String pinyin = "";
            try {
                word = new String(bytes, "gb2312");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                i--;
                continue;
            }
            //过滤一些消极的字
            if ("死".equals(word) || "病".equals(word)) {
                i--;
                continue;
            }
            try {
                pinyin = PinyinHelper.convertToPinyinString(word, ",", PinyinFormat.WITHOUT_TONE);
                phonetic = PinyinHelper.convertToPinyinString(word, ",", PinyinFormat.WITH_TONE_MARK);
            } catch (PinyinException e) {
                e.printStackTrace();

            }
            log.info("生成汉字:" + word);
            String encode = URLEncoder.encode(word, StandardCharsets.UTF_8);
            log.info("UTF-8: " + encode);
            Map<String, Object> wordwrap = new HashMap<>(16);
            wordwrap.put("word", word);
            wordwrap.put("pinyin", pinyin);
            wordwrap.put("phonetic", phonetic);
            wordwrap.put("encode", encode);
            wordList.add(wordwrap);

        }
        object.put("wordList", wordList);
        object.put("statusCode", "0");

        return commonResult.success(object);
    }

    public static void main(String[] args) {
        CommonResult<JSON> invoke = invoke(5);
        System.out.println(invoke.toString());
    }
}
