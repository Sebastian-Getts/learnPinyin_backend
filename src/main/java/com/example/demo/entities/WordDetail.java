package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 单个汉字的详细信息
 *
 * @author sebastiangetts
 */
@Setter
@Getter
@Accessors(chain = true)
@ToString
public class WordDetail {

    /**
     * 汉字
     */
    private String word;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * ?
     */
    private String phonetic;

    /**
     * 编码
     */
    private String encode;
}
