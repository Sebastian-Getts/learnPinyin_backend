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

/**
 * @author sebastian
 * @date 2020/6/7 0:11
 */
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
    public CommonResult<String> insert(CollectWords collectWords) {
        CommonResult<String> commonResult = new CommonResult<>();
        try {
            UserList byId = mongoOperations.findById(collectWords.getOpenid(), UserList.class);
            if (StringUtils.isEmpty(byId)) {
                UserList list = new UserList();
                list.setOpenId(collectWords.getOpenid());
                Map<String, CollectWords> map = new HashMap<>(16);
                map.put(collectWords.getEncode(), collectWords);
                list.setItems(map);
                mongoOperations.save(list);
            } else {
                Map<String, CollectWords> items = byId.getItems();
                if (items.containsKey(collectWords.getEncode())) {
                    commonResult.fail("收藏的汉字已存在！");
                    return commonResult;
                }
                items.put(collectWords.getEncode(), collectWords);
                byId.setItems(items);
                mongoOperations.save(byId);
            }
            commonResult.success("收藏成功！");
        } catch (Exception e) {
            e.printStackTrace();
            commonResult.fail("System error");
        }
        return commonResult;
    }

    /**
     * 查询数据
     *
     * @param openid 用户openid
     * @return data中为数据集合
     */
    public CommonResult<Collection<CollectWords>> query(String openid) {
        UserList byId = mongoOperations.findById(openid, UserList.class);
        Collection<CollectWords> values;
        CommonResult<Collection<CollectWords>> commonResult = new CommonResult<>();
        if (byId != null) {
            values = byId.getItems().values();
            List<CollectWords> list = new ArrayList<>(values);
            list.sort((o1, o2) -> o2.getTimestamp().compareTo(o1.getTimestamp()));
            commonResult.success(list);
        } else {
            commonResult.success(null);
        }
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
            UserList byId = mongoOperations.findById(collectWords.getOpenid(), UserList.class);
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
    public CommonResult<String> deleteAll(String openId) {
        UserList byId = mongoOperations.findById(openId, UserList.class);
        assert byId != null;
        Map<String, CollectWords> items = byId.getItems();
        if (items.isEmpty()) {
            log.info("========already empty !!!");
            return new CommonResult<String>().fail("没有收藏的文字哦！");
        }
        items.clear();
        byId.setItems(items);
        mongoOperations.save(byId);
        return new CommonResult<String>().success("清除成功！");
    }
}
