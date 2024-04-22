package com.huaihao.bookcrosser.backend.mbg.mapper;

import com.huaihao.bookcrosser.backend.mbg.model.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> selectAll();

    Book selectById(@Param("id") Long id);

    boolean save(Book book);

    boolean updateById(Book book);

    boolean deleteById(@Param("id") Long id);

    List<Book> searchByTitle(@Param("title") String title);

    List<Book> searchByAuthor(@Param("author") String author);

    List<Book> searchByIsbn(@Param("isbn") String isbn);

    List<Book> loadBooksByOwnerId(@Param("ownerId") Long ownerId);

    List<Book> loadBooksByUploaderId(@Param("uploaderId") Long uploaderId);

    List<Book> search(@Param("title") String title, @Param("author") String author, @Param("exact") boolean exact);

    boolean deleteAll();


}
