package com.huaihao.bookcrosser.backend.controller;

import com.huaihao.bookcrosser.backend.mbg.model.Book;
import com.huaihao.bookcrosser.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/selectAll")
    public List<Book> selectAll() {
        return bookService.selectAll();
    }

    @GetMapping("/selectById")
    public Book selectById(Long id) {
        return bookService.selectById(id);
    }

    @PostMapping("/save")
    public boolean save(Book book) {
        return bookService.save(book);
    }

    @PostMapping("/update")
    public boolean update(Book book) {
        return bookService.updateById(book);
    }

    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable("id") Long id) {
        return bookService.deleteById(id);
    }

    @GetMapping("/searchByTitle")
    public List<Book> searchByTitle(String title) {
        return bookService.searchByTitle(title);
    }

    @GetMapping("/searchByAuthor")
    public List<Book> searchByAuthor(String author) {
        return bookService.searchByAuthor(author);
    }

    @GetMapping("/searchByIsbn")
    public List<Book> searchByIsbn(String isbn) {
        return bookService.searchByIsbn(isbn);
    }
}
