package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.entities.CollectWords;
import com.example.demo.entities.CommonResult;
import com.example.demo.service.CollectSingleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;

/**
 * @author sebastian
 * @date 07/06/2020 22:00
 */
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
    public CommonResult<String> collectWord(@RequestBody CollectWords collectWords) {
        log.debug("object from remote: {}", collectWords.toString());
        return collectSingleService.insert(collectWords);
    }

    /**
     * 查询用户名下word清单
     *
     * @param map 存放用户openId，唯一标识
     * @return common result
     */
    @PostMapping("wordList")
    public CommonResult<Collection<CollectWords>> getWords(@RequestBody Map<String, Object> map) {
        if (StringUtils.isEmpty(map)) {
            return new CommonResult<Collection<CollectWords>>().fail("获取openid异常~");
        }
        String openid = (String) map.get("openid");
        return collectSingleService.query(openid);
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
     * @param map storage user openId
     * @return common result
     */
    @PostMapping("wordMinusAll")
    public CommonResult<String> deleteWordsAll(@RequestBody Map<String, Object> map) {
        if (StringUtils.isEmpty(map)) {
            return new CommonResult<String>().fail("获取openid异常~");
        }
        String openid = (String) map.get("openid");
        return collectSingleService.deleteAll(openid);
    }

    @PostMapping("deleteByMonth")
    public CommonResult<String> deleteWordsByMonth(@RequestBody Map<String, Object> map) {
        if (StringUtils.isEmpty(map)) {
            return new CommonResult<String>().fail("获取信息异常~");
        }
        String openid = (String) map.get("openid");
        String month = (String) map.get("month");
        String year = (String) map.get("year");
        return collectSingleService.deleteByMonth(year, month, openid);
    }

    /**
     * 获取所有用户
     *
     * @return 用户列表
     */
    @GetMapping("allUsers")
    public CommonResult<Object> getAllUsers() {
        return collectSingleService.getAllUsers();
    }

    /**
     * 按月查询
     *
     * @param map 日期、openid
     * @return CollectWords
     */
    @PostMapping("filterWordByMonth")
    public CommonResult<Collection<CollectWords>> wordsByMonth(@RequestBody Map<String, Object> map) {
        if (!map.isEmpty()) {
            String year = (String) map.get("year");
            String month = (String) map.get("month");
            String openid = (String) map.get("openid");
            return collectSingleService.getWordsByMonth(year, month, openid);
        }
        return new CommonResult<Collection<CollectWords>>().fail("Input error.");
    }
}
