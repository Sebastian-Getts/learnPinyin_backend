package com.example.demo.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sebastian
 * @date 2020/5/10 21:03
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum  JuheEnums {
    //新华字典
    DICTIONARY("1b745281c1a6d12ddc86f713f0751639","http://v.juhe.cn/xhzd/query", "dictionary"),
    //历史上的今天
    HISTORY("932fd4af7108077e08e41e1ced54f9f2","http://api.juheapi.com/japi/toh","history");


    private final String appKey;
    private final String url;
    private final String type;
}
