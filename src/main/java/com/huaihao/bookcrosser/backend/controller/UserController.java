package com.huaihao.bookcrosser.backend.controller;

import com.huaihao.bookcrosser.backend.mbg.model.User;
import com.huaihao.bookcrosser.backend.mbg.model.UserProfile;
import com.huaihao.bookcrosser.backend.service.Result;
import com.huaihao.bookcrosser.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Result> register(@RequestBody User user) {
        Result result = userService.register(user);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Result> login(@RequestBody User user) {
        Result result = userService.login(user);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @GetMapping("/checkLogin")
    public ResponseEntity<Result> checkLogin(@RequestAttribute("userId") Long userId) {
        Result result = userService.checkLogin(userId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @GetMapping("/loadUserProfile")
    public ResponseEntity<UserProfile> loadUserProfile(@RequestAttribute("userId") Long userId) {
        UserProfile profile = userService.loadUserProfile(userId);
        if (profile != null) {
            return ResponseEntity.ok(profile);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/update")
    public boolean update(
            @RequestParam("username") String username,
            @RequestParam("bio") String bio,
            @RequestParam("latitude") Double latitude,
            @RequestParam("longitude") Double longitude,
            @RequestAttribute("userId") Long userId
    ) {
        System.out.println("UserController update: " + username);
        System.out.println("UserController update: " + userId);
        return userService.updateById(username, bio, latitude, longitude, userId);
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
