package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.entities.CollectWords;
import com.example.demo.entities.CommonResult;
import com.example.demo.service.CollectSingleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

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
        return collectSingleService.insert(collectWords);
    }

    /**
     * 查询用户名下word个数
     *
     * @param openId 用户openId，唯一标识
     * @return common result
     */
    @GetMapping("word/{openId}")
    public CommonResult<Collection<CollectWords>> getWords(@PathVariable("openId") String openId) {
        return collectSingleService.query(openId);
    }

    /**
     * 删除单个word
     *
     * @param collectWords reference collectWords entity
     * @return common result
     */
    @PostMapping("wordMinus")
    public CommonResult<JSON> deleteWords(@RequestBody CollectWords collectWords) {
        return collectSingleService.delete(collectWords);
    }

    /**
     * 删除用户所有的words
     *
     * @param openId user openId
     * @return common result
     */
    @DeleteMapping("word/{openId}")
    public CommonResult<JSON> deleteWordsAll(@PathVariable("openId") String openId) {
        return collectSingleService.deleteAll(openId);
    }
}
