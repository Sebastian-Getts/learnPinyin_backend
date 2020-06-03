package com.example.demo.dao;

import com.example.demo.entities.Collections;
import com.example.demo.entities.UserList;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

public interface UserListDao extends MongoRepository<UserList, String>{
    List<UserList> findByOpenId(String openId);

    List<UserList> findByGender(String gender);

    void deleteByOpenId(String openId);

    long countAllByOpenId(String openId);

    boolean existsUserListByOpenId(String openId);

}
