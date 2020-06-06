package com.example.demo.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@Data
@Setter
@Getter
@ToString
public class CollectWords implements Collections{
    private String openId;
    private String word;
    private String encode;
    private String phonetic;
}
