package com.huaihao.bookcrosser.backend.controller;

import com.huaihao.bookcrosser.backend.mbg.model.User;
import com.huaihao.bookcrosser.backend.service.Result;
import com.huaihao.bookcrosser.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public boolean save(User user) {
        return userService.save(user);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/update")
    public boolean update(User user) {
        user.setUpdatedAt(LocalDateTime.now());
        return userService.updateById(user);
    }

    @GetMapping("/selectAll")
    public List<User> selectAll() {
        return userService.selectAll();
    }

    @GetMapping("/selectById")
    public User selectById(Long id) {
        return userService.selectById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable("id") Long id) {
        return userService.deleteById(id);
    }

    @DeleteMapping("/deleteByEmail/{email}")
    public boolean deleteByEmail(@PathVariable("email") String email) {
        return userService.deleteByEmail(email);
    }
}
