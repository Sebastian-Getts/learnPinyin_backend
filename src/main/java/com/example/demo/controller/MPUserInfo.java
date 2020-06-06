package com.example.demo.controller;

import ch.qos.logback.core.util.StringCollectionUtil;
import com.alibaba.fastjson.JSON;
import com.example.demo.entities.CommonResult;
import com.example.demo.service.MPUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * 获取用户信息
 *
 * @author sebastian
 * @date 2020/6/6 22:37
 */
@RequestMapping("wx")
@RestController
@Slf4j
public class MPUserInfo {

    @Resource
    MPUserInfoService mpUserInfoService;

    @GetMapping("userInfo/{code}")
    public CommonResult<JSON> getCode(@PathVariable("code") String code) throws URISyntaxException {
        if (StringUtils.isEmpty(code)) {
            return new CommonResult<JSON>().fail("code is empty ...");
        }
        return mpUserInfoService.commit(code);
    }
}
