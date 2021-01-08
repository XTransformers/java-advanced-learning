package com.xtransformers.service.impl;

import com.xtransformers.dao.UserMapper;
import com.xtransformers.entity.User;
import com.xtransformers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User find(int id) {
        return userMapper.find(id);
    }

    @Override
    public List<User> list() {
        return userMapper.list();
    }

}
