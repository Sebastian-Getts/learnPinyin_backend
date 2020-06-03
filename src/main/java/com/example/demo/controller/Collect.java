package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entities.CollectWords;
import com.example.demo.entities.CommonResult;
import com.example.demo.service.CollectSingleService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("collect")
public class Collect {

    @Resource
    CollectSingleService collectSingleService;

    /**
     * 收藏单个字
     *
     * @param collectWords 传入的对象，单个文字，应包含字、拼音、encode, openid
     * @return common result
     */
    @PostMapping("word")
    public CommonResult<JSON> collectWord(@RequestBody CollectWords collectWords) {
        log.info("object from remote: {}", collectWords.toString());
        CommonResult<JSON> commonResult = new CommonResult<>();
        commonResult = collectSingleService.insert(collectWords);
        return commonResult;
    }

    @GetMapping("word/{openId}")
    public CommonResult<Collection<CollectWords>> getWords(@PathVariable() String openId) {
        return collectSingleService.query(openId);
    }

}
