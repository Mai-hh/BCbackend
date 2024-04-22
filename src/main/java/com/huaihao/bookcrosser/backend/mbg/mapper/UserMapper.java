package com.huaihao.bookcrosser.backend.mbg.mapper;

import com.huaihao.bookcrosser.backend.mbg.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectAll();

    User selectById(@Param("id") Long id);

    boolean save(User user);

    boolean updateById(@Param("username") String username,
                       @Param("bio") String bio,
                       @Param("latitude") Double latitude,
                       @Param("longitude") Double longitude,
                       @Param("userId") Long userId);

    boolean deleteById(@Param("id") Long id);

    boolean deleteByEmail(@Param("email") String email);

    User selectByUsername(@Param("username") String username);

    User selectByEmail(@Param("email") String email);

}
