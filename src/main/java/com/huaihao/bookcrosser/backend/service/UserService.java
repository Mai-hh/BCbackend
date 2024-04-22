package com.huaihao.bookcrosser.backend.service;

import com.huaihao.bookcrosser.backend.mbg.model.User;
import com.huaihao.bookcrosser.backend.mbg.model.UserProfile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    List<User> selectAll();

    User selectById(@Param("id") Long id);

    boolean save(User user);

    boolean updateById(User user);

    boolean deleteById(@Param("id") Long id);

    boolean deleteByEmail(@Param("email") String email);

    Result register(User user);

    Result login(User user);

    Result checkLogin(Long id);

    User getByUsername(String username);

    UserProfile loadUserProfile(Long id);
}
