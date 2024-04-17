package com.huaihao.bookcrosser.backend.service.impl;

import com.huaihao.bookcrosser.backend.common.JWTUtil;
import com.huaihao.bookcrosser.backend.mbg.mapper.UserMapper;
import com.huaihao.bookcrosser.backend.mbg.model.User;
import com.huaihao.bookcrosser.backend.service.Result;
import com.huaihao.bookcrosser.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public User selectById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public boolean save(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userMapper.save(user);
    }

    @Override
    public boolean updateById(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public boolean deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public boolean deleteByEmail(String email) {
        return userMapper.deleteByEmail(email);
    }

    @Override
    public Result register(User user) {
        // 检查用户名是否已存在
        if (userMapper.selectByUsername(user.getUsername()) != null) {
            return Result.failed("用户名已存在");
        }
        // 检查邮箱是否已存在
        if (userMapper.selectByEmail(user.getEmail()) != null) {
            return Result.failed("邮箱已存在");
        }
        // 保存用户信息到数据库
        if (save(user)) {
            return Result.success("注册成功");
        } else {
            return Result.failed("注册失败");
        }
    }

    @Override
    public Result login(User user) {
        // 根据用户名查询用户
        User dbUser = userMapper.selectByEmail(user.getEmail());
        if (dbUser == null) {
            return Result.failed("用户不存在");
        }
        // 检查密码是否正确
        if (!dbUser.getPassword().equals(user.getPassword())) {
            return Result.failed("密码错误");
        }
        // 生成 JWT token
        String token = JWTUtil.generateToken(dbUser.getId());
        // 返回 token 给前端
        return Result.loginSuccess(token);
    }

    @Override
    public Result checkLogin(String token) {
        if (token != null && JWTUtil.validateToken(token)) {
            Long userId = JWTUtil.parseToken(token);
            User user = selectById(userId);
            if (user != null) {
                return Result.success(user);
            }
        }
        return Result.failed("用户未登录");
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}
