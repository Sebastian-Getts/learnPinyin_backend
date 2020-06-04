package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.example.demo.entities.CollectWords;
import com.example.demo.entities.CommonResult;
import com.example.demo.entities.UserList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class CollectSingleService {

    @Resource
    MongoOperations mongoOperations;

    /**
     * 收藏文字（插入数据）
     *
     * @param collectWords 参见实体类
     * @return 参见common result  实体类
     */
    public CommonResult<JSON> insert(CollectWords collectWords) {
        CommonResult<JSON> commonResult = new CommonResult<>();
        try {
            UserList byId = mongoOperations.findById(collectWords.getOpenId(), UserList.class);
            if (StringUtils.isEmpty(byId)) {
                UserList list = new UserList();
                list.setOpenId(collectWords.getOpenId());
                Map<String, CollectWords> map = new HashMap<>();
                map.put(collectWords.getEncode(), collectWords);
                list.setItems(map);
                mongoOperations.save(list);
            } else {
                Map<String, CollectWords> items = byId.getItems();
                if (items.containsKey(collectWords.getEncode())) {
                    commonResult.fail("The word has already existed!!!");
                    return commonResult;
                }
                items.put(collectWords.getEncode(), collectWords);
                byId.setItems(items);
                mongoOperations.save(byId);
            }
            commonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            commonResult.fail("System error");
        }
        return commonResult;
    }

    /**
     * 查询数据
     *
     * @param openId 用户openId
     * @return data中为数据集合
     */
    public CommonResult<Collection<CollectWords>> query(String openId) {
        UserList byId = mongoOperations.findById(openId, UserList.class);
        Collection<CollectWords> values = null;
        if (byId != null) {
            values = byId.getItems().values();
        }
        CommonResult<Collection<CollectWords>> commonResult = new CommonResult<>();
        commonResult.success(values);
        return commonResult;
    }


    /**
     * 删除word
     *
     * @param collectWords 参见entities
     * @return common result
     */
    public CommonResult<JSON> delete(CollectWords collectWords) {
        CommonResult<JSON> commonResult = new CommonResult<>();
        try {
            UserList byId = mongoOperations.findById(collectWords.getOpenId(), UserList.class);
            assert byId != null;
            Map<String, CollectWords> items = byId.getItems();
            items.remove(collectWords.getEncode());
            byId.setItems(items);
            mongoOperations.save(byId);
            commonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            commonResult.fail("数据库操作异常～");
        }
        return commonResult;
    }

    /**
     * 删除所有words
     *
     * @param openId user weChat MiniProgram openId
     * @return common result
     */
    public CommonResult<JSON> deleteAll(String openId) {
        UserList byId = mongoOperations.findById(openId, UserList.class);
        assert byId != null;
        Map<String, CollectWords> items = byId.getItems();
        items.clear();
        byId.setItems(items);
        mongoOperations.save(byId);
        return new CommonResult<JSON>().success();
    }
}
