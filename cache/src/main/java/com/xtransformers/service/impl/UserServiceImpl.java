package com.xtransformers.service.impl;

import com.xtransformers.dao.UserMapper;
import com.xtransformers.entity.User;
import com.xtransformers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "userCache")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable(key = "#id")
    public User find(int id) {
        return userMapper.find(id);
    }

    @Override
    @Cacheable(key = "methodName")
    public List<User> list() {
        return userMapper.list();
    }

}
