package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.JuHeService;
import com.example.demo.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.URISyntaxException;

/**
 * "聚合接口"提供服务
 *
 * @author sebastian
 * @date 2020/5/10 21:39
 */
@RestController
@Slf4j
@RequestMapping("juhe")
public class Juhe {

    @Resource
    JuHeService juheService;

    @GetMapping("history/{month}/{day}")
    public CommonResult<JSON> history(@PathVariable("month") String month, @PathVariable("day") String day) throws URISyntaxException {
        JSONObject object = new JSONObject();
        object.put("month", month);
        object.put("day", day);

        return juheService.invoke("history", object);
    }

    @GetMapping("dictionary/{word}")
    public CommonResult<JSON> dictionary(@PathVariable("word") String word) throws URISyntaxException {
        JSONObject object = new JSONObject();
        object.put("word", word);
        return juheService.invoke("dictionary", object);
    }

}
