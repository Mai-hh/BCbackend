package com.huaihao.bookcrosser.backend.service.impl;

import com.huaihao.bookcrosser.backend.mbg.mapper.BookMapper;
import com.huaihao.bookcrosser.backend.mbg.model.Book;
import com.huaihao.bookcrosser.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> selectAll() {
        return bookMapper.selectAll();
    }

    @Override
    public Book selectById(Long id) {
        return bookMapper.selectById(id);
    }

    @Override
    public boolean save(Book book) {
        book.setCreatedAt(LocalDateTime.now());
        book.setUpdatedAt(LocalDateTime.now());
        return bookMapper.save(book);
    }

    @Override
    public boolean updateById(Book book) {
        return bookMapper.update(book);
    }

    @Override
    public boolean deleteById(Long id) {
        return bookMapper.deleteById(id);
    }

    @Override
    public List<Book> searchByTitle(String title) {
        return bookMapper.searchByTitle(title);
    }

    @Override
    public List<Book> searchByAuthor(String author) {
        return bookMapper.searchByAuthor(author);
    }

    @Override
    public List<Book> searchByIsbn(String isbn) {
        return bookMapper.searchByIsbn(isbn);
    }

    @Override
    public List<Book> search(String title, String author, boolean exact) {
        return bookMapper.search(title, author, exact);
    }
}
