package com.huaihao.bookcrosser.backend.service;

import com.huaihao.bookcrosser.backend.mbg.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    List<User> selectAll();

    User selectById(@Param("id") Long id);

    boolean save(User user);

    boolean updateById(User user);

    boolean deleteById(@Param("id") Long id);
}
