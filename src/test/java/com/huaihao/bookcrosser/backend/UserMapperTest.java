package com.huaihao.bookcrosser.backend;

import com.huaihao.bookcrosser.backend.mbg.mapper.UserMapper;
import com.huaihao.bookcrosser.backend.mbg.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
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
        Long userId = 1L;
        User user = userMapper.selectById(userId);
        assertNotNull(user);
        assertEquals(userId, user.getId());
        // 添加其他断言语句，例如检查用户的其他属性
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("test@example.com");
        // 设置其他必要的属性

        boolean result = userMapper.save(user);
        assertTrue(result);
        assertNotNull(user.getId());
    }

    @Test
    public void testUpdateById() {
        Long userId = 1L;
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
        Long userId = 1L;
        boolean result = userMapper.deleteById(userId);
        assertTrue(result);

        User deletedUser = userMapper.selectById(userId);
        assertNull(deletedUser);
    }

}
