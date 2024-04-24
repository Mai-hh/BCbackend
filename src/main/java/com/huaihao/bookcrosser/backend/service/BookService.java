package com.huaihao.bookcrosser.backend.service;

import com.huaihao.bookcrosser.backend.mbg.model.Book;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookService {
    List<Book> selectAll();

    Book selectById(@Param("id") Long id);

    boolean save(Book book);

    boolean updateById(Book book);

    Result updateCommon(
            @Param("id")    Long id,
            @Param("title") String title,
            @Param("author")  String author,
            @Param("description")   String description,
            @Param("updatedAt") LocalDateTime updatedAt
    );

    boolean deleteById(@Param("id") Long id);

    List<Book> searchByTitle(@Param("title") String title);

    List<Book> searchByAuthor(@Param("author") String author);

    List<Book> searchByIsbn(@Param("isbn") String isbn);

    List<Book> search(@Param("title") String title, @Param("author") String author, @Param("exact") boolean exact, @Param("userId") Long userId);
}
