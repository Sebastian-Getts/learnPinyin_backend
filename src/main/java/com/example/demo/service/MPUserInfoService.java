package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.example.demo.config.MiniProgramInfoConfig;
import com.example.demo.entities.CommonResult;
import com.example.demo.utils.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URISyntaxException;

/**
 * @author sebastian
 * @date 2020/6/6 22:40
 */
@Service
@Slf4j
public class MPUserInfoService {

    @Resource
    HttpRequest httpRequest;

    public CommonResult<JSON> commit(String code) throws URISyntaxException {
        log.info("============this is Logger============");

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + MiniProgramInfoConfig.getAppId() +
                "&secret=" + MiniProgramInfoConfig.getAppSecret() + "&js_code=" + code + "&grant_type=authorization_code";
        log.info("===========url: " + url);
        CommonResult<JSON> commonResult = httpRequest.httpGet(null, url);
        JSON data = commonResult.getData();
        log.info("============response: " + data.toJSONString());
        return commonResult;
    }
}
