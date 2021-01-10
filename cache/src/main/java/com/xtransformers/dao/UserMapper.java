package com.xtransformers.dao;

import com.xtransformers.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    User find(int id);

    List<User> list();

    int add(@Param("user") User user);

    int delete(int id);

    int update(@Param("user") User user);

}
