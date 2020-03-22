package com.example.demo.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author sebastian
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum  JuheEnums {
    //新华字典
    DICTIONARY("***********","http://v.juhe.cn/xhzd/query", "dictionary"),
    //历史上的今天
    HISTORY("***********","http://api.juheapi.com/japi/toh","history");


    private String appKey;
    private String url;
    private String type;
}
