package com.huaihao.bookcrosser.backend;

import com.huaihao.bookcrosser.backend.mbg.mapper.UserMapper;
import com.huaihao.bookcrosser.backend.mbg.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectAll() {
        List<User> users = userMapper.selectAll();
        assertNotNull(users);
        // 添加其他断言语句，例如检查用户列表的大小或特定用户的属性
    }

    @Test
    public void testSelectById() {
        Long userId = 2L;
        User user = userMapper.selectById(userId);
        // 添加其他断言语句，例如检查用户的其他属性
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("test@example.com");
        user.setAvatar("a");
        boolean result = userMapper.save(user);
        assertTrue(result);
    }

    @Test
    public void testUpdateById() {
        Long userId = 2L;
        User user = userMapper.selectById(userId);
        user.setUsername("updateduser");
        user.setEmail("updated@example.com");
        // 更新其他属性

        boolean result = userMapper.updateById(user);
        assertTrue(result);

        User updatedUser = userMapper.selectById(userId);
        assertEquals("updateduser", updatedUser.getUsername());
        assertEquals("updated@example.com", updatedUser.getEmail());
        // 添加其他断言语句，检查更新后的用户属性
    }

    @Test
    public void testDeleteById() {
        Long userId = 2L;
        boolean result = userMapper.deleteById(userId);
        assertTrue(result);

        User deletedUser = userMapper.selectById(userId);
        assertNull(deletedUser);
    }
}
