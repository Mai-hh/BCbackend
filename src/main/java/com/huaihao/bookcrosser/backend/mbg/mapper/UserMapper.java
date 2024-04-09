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

    boolean updateById(User user);

    boolean deleteById(@Param("id") Long id);

}
