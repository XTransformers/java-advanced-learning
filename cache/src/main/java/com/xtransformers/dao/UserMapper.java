package com.xtransformers.dao;

import com.xtransformers.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    User find(int id);

    List<User> list();

}
