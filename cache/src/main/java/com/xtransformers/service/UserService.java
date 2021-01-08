package com.xtransformers.service;

import com.xtransformers.entity.User;

import java.util.List;

public interface UserService {

    User find(int id);

    List<User> list();

}
