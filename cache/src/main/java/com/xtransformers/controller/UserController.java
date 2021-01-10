package com.xtransformers.controller;

import com.xtransformers.entity.User;
import com.xtransformers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //  GET：查询，POST：新增，PUT：更新，DELETE：删除

    @GetMapping("/{id}")
    public User find(@PathVariable("id") int id) {
        return userService.find(id);
    }

    @GetMapping
    public List<User> list() {
        return userService.list();
    }

    @PostMapping
    public User add(@RequestBody User user) {
        return userService.add(user);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable int id) {
        return userService.delete(id);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

}
