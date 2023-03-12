package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.example.demo.entities.WordDetail;
import com.example.demo.service.PickOneService;
import com.example.demo.entities.CommonResult;
import com.example.demo.utils.annotations.Aes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sebastian
 * @date 2020/5/10 21:03
 */
@RestController
@Slf4j
@RequestMapping("/pickOne")
public class PickOne {

    @Resource
    PickOneService pickOneService;

    @GetMapping("/word/{count}")
    public CommonResult<List<WordDetail>> oneWord(@PathVariable("count") int count) {

        return pickOneService.invoke(count);
    }

    @GetMapping("/phrase/{count}")
    public CommonResult<Map<String, Object>> onePhrase(@PathVariable("count") int count) {
        Map<String, Object> map = new HashMap<>(16);

        return new CommonResult<>(200, "返回成功", map);
    }

}
