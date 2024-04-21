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
        // 创建图书对象
        Book book1 = new Book();
        book1.setTitle("To Kill a Mockingbird");
        book1.setAuthor("Harper Lee");
        book1.setIsbn("9780446310789");
        book1.setOwnerId(6L);
        book1.setUploaderId(6L);
        book1.setDescription("A classic novel about racial injustice and moral growth in the American South.");
        book1.setCoverUrl("https://example.com/covers/to-kill-a-mockingbird.jpg");
        book1.setLongitude(-88.2039);
        book1.setLatitude(32.3668);
        book1.setStatus("available");
        book1.setCreatedAt(LocalDateTime.now());
        book1.setUpdatedAt(LocalDateTime.now());

        Book book2 = new Book();
        book2.setTitle("1984");
        book2.setAuthor("George Orwell");
        book2.setIsbn("9780451524935");
        book2.setOwnerId(6L);
        book2.setUploaderId(6L);
        book2.setDescription("A dystopian novel set in a totalitarian society ruled by a tyrannical government.");
        book2.setCoverUrl("https://example.com/covers/1984.jpg");
        book2.setLongitude(-0.1278);
        book2.setLatitude(51.5074);
        book2.setStatus("available");
        book2.setCreatedAt(LocalDateTime.now());
        book2.setUpdatedAt(LocalDateTime.now());

        Book book3 = new Book();
        book3.setTitle("Pride and Prejudice");
        book3.setAuthor("Jane Austen");
        book3.setIsbn("9780141439518");
        book3.setOwnerId(6L);
        book3.setUploaderId(6L);
        book3.setDescription("A classic romance novel about the Bennet family and their five unmarried daughters.");
        book3.setCoverUrl("https://example.com/covers/pride-and-prejudice.jpg");
        book3.setLongitude(-1.2577);
        book3.setLatitude(51.7520);
        book3.setStatus("borrowed");
        book3.setCreatedAt(LocalDateTime.now());
        book3.setUpdatedAt(LocalDateTime.now());

        Book book4 = new Book();
        book4.setTitle("The Great Gatsby");
        book4.setAuthor("F. Scott Fitzgerald");
        book4.setIsbn("9780743273565");
        book4.setOwnerId(6L);
        book4.setUploaderId(6L);
        book4.setDescription("A novel about decadence, idealism, social upheaval, and excess in the Jazz Age.");
        book4.setCoverUrl("https://example.com/covers/the-great-gatsby.jpg");
        book4.setLongitude(-73.9857);
        book4.setLatitude(40.7484);
        book4.setStatus("available");
        book4.setCreatedAt(LocalDateTime.now());
        book4.setUpdatedAt(LocalDateTime.now());

        Book book5 = new Book();
        book5.setTitle("One Hundred Years of Solitude");
        book5.setAuthor("Gabriel García Márquez");
        book5.setIsbn("9780060883287");
        book5.setOwnerId(6L);
        book5.setUploaderId(6L);
        book5.setDescription("A landmark novel that tells the multi-generational story of the Buendía family.");
        book5.setCoverUrl("https://example.com/covers/one-hundred-years-of-solitude.jpg");
        book5.setLongitude(-74.2973);
        book5.setLatitude(4.7110);
        book5.setStatus("borrowed");
        book5.setCreatedAt(LocalDateTime.now());
        book5.setUpdatedAt(LocalDateTime.now());

        // 保存图书到数据库
        bookMapper.save(book1);
        bookMapper.save(book2);
        bookMapper.save(book3);
        bookMapper.save(book4);
        bookMapper.save(book5);

        // 验证插入的图书是否存在于数据库中
        List<Book> books = bookMapper.selectAll();
        assertNotNull(books);
        assertTrue(books.size() >= 5);

    }

    @Test
    public void testSelectAll() {
        List<Book> books = bookMapper.selectAll();
        assertNotNull(books);
    }

    @Test
    public void testDeleteAll() {
        bookMapper.deleteAll();
        List<Book> books = bookMapper.selectAll();
        assertNotNull(books);
        assertTrue(books.isEmpty());
    }

}
