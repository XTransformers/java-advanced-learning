package com.xtransformers.service;

import com.xtransformers.entity.User;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

@CacheConfig(cacheNames = "users")
public interface UserService {

    User find(int id);

    List<User> list();

    User add(User user);

    int delete(int id);

    User update(User user);

}
