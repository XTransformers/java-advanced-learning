package com.xtransformers.service.impl;

import com.xtransformers.dao.UserMapper;
import com.xtransformers.entity.User;
import com.xtransformers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@CacheConfig(cacheNames = "userCache")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
//    @Cacheable(key = "#id")
    @Cacheable
    public User find(int id) {
        return userMapper.find(id);
    }

    @Override
//    @Cacheable(key = "methodName")
    @Cacheable
    public List<User> list() {
        return userMapper.list();
    }

    @Override
//    @CachePut(key = "#user.id")
    @CachePut
    public User add(User user) {
        userMapper.add(user);
        return user;
    }

    @Override
//    @CacheEvict(key = "#id")
    @CacheEvict
    public int delete(int id) {
        return userMapper.delete(id);
    }

    @Override
//    @CachePut(key = "#user.id")
    @CachePut
    public User update(User user) {
        userMapper.update(user);
        return user;
    }

}
