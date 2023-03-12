package com.example.demo.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Map;


/**
 * @author sebastian
 * @date 2020/6/7 11:18
 */
//@Document
@Getter
@Setter
@Data
public class UserList {
//    @Id
    private String openId;
    private String gender;
    private Map<String, CollectWords> items;
}
