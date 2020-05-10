package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.HttpRequest;
import com.example.demo.entities.CommonResult;
import com.example.demo.enums.JuheEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URISyntaxException;

/**
 * @author sebastian
 * @date 2020/5/10 21:03
 */
@Service
@Slf4j
public class JuHeService {

    @Resource
    HttpRequest httpRequest;

    public CommonResult<JSON> invoke(String type, JSONObject parameters) throws URISyntaxException {
        String url;
        if (JuheEnums.DICTIONARY.getType().equals(type)) {
            url = JuheEnums.DICTIONARY.getUrl();
            parameters.put("key", JuheEnums.DICTIONARY.getAppKey());
        } else {
            url = JuheEnums.HISTORY.getUrl();
            parameters.put("key", JuheEnums.HISTORY.getAppKey());
        }
        return httpRequest.httpGet(parameters, url);
    }
}
