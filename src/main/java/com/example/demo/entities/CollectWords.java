package com.example.demo.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @author sebastian
 * @date 2020/6/7 9:51
 */
@Data
@Setter
@Getter
@ToString
public class CollectWords implements Collections {
    private String openid;
    private String word;
    private String encode;
    private String phonetic;
    private String timestamp;
}
