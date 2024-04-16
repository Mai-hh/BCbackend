package com.huaihao.bookcrosser.backend;

import com.huaihao.bookcrosser.backend.mbg.mapper.BookMapper;
import com.huaihao.bookcrosser.backend.mbg.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void testSave() {
        Book book = new Book();
        book.setTitle("testbook");
        book.setAuthor("testauthor");
        book.setIsbn("1234567890");
        book.setCoverUrl("cover");
        book.setDescription("description");
        book.setCreatedAt(LocalDateTime.now());
        book.setUpdatedAt(LocalDateTime.now());
        boolean result = bookMapper.save(book);
        assertTrue(result);
    }

    @Test
    public void testSelectAll() {
        List<Book> books = bookMapper.selectAll();
        assertNotNull(books);
    }

}
