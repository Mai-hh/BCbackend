package com.huaihao.bookcrosser.backend.service.impl;

import com.huaihao.bookcrosser.backend.common.JWTUtil;
import com.huaihao.bookcrosser.backend.mbg.mapper.BookMapper;
import com.huaihao.bookcrosser.backend.mbg.mapper.DriftingMapper;
import com.huaihao.bookcrosser.backend.mbg.mapper.UserMapper;
import com.huaihao.bookcrosser.backend.mbg.model.Book;
import com.huaihao.bookcrosser.backend.mbg.model.DriftingRecord;
import com.huaihao.bookcrosser.backend.mbg.model.User;
import com.huaihao.bookcrosser.backend.mbg.model.UserProfile;
import com.huaihao.bookcrosser.backend.service.Result;
import com.huaihao.bookcrosser.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    DriftingMapper driftingMapper;

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
    public boolean updateById(String username, String bio, Double latitude, Double longitude, Long userId) {
        return userMapper.updateById(username, bio, latitude, longitude, userId);
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
            String token = JWTUtil.generateToken(userMapper.selectByEmail(user.getEmail()).getId());
            return Result.loginSuccess(token);
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
    public Result checkLogin(Long userId) {
        User user = selectById(userId);
        if (user != null) {
            return Result.success(user);
        }

        return Result.failed("用户未登录");
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public UserProfile loadUserProfile(Long userId) {
        User user = selectById(userId);

        if (user != null) {
            List<Book> booksUploaded = bookMapper.loadBooksByUploaderId(user.getId());

            List<DriftingRecord> requests = driftingMapper.selectByRequesterId(user.getId());
            List<Book> booksInRequesting = new ArrayList<>();
            for (DriftingRecord request : requests) {
                if (request.getOwnerId().equals(userId)) {
                    continue;
                }
                Long bookId = request.getBookId();
                booksInRequesting.add(bookMapper.selectById(bookId));
            }

            List<Book> booksBorrowed = bookMapper.loadBooksByOwnerId(user.getId());

            UserProfile userProfile = new UserProfile();
            userProfile.setId(user.getId());
            userProfile.setUsername(user.getUsername());
            userProfile.setEmail(user.getEmail());
            userProfile.setLatitude(user.getLatitude());
            userProfile.setLongitude(user.getLongitude());
            userProfile.setAvatar(user.getAvatar());
            userProfile.setBio(user.getBio());
            userProfile.setBooksUploaded(booksUploaded);
            userProfile.setBooksBorrowed(booksBorrowed);
            userProfile.setBooksInRequesting(booksInRequesting);
            return userProfile;
        } else {
            return null;
        }
    }
}
