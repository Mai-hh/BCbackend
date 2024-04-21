package com.huaihao.bookcrosser.backend.controller;

import com.huaihao.bookcrosser.backend.mbg.model.Book;
import com.huaihao.bookcrosser.backend.mbg.model.BookStatus;
import com.huaihao.bookcrosser.backend.service.BookService;
import com.huaihao.bookcrosser.backend.service.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/shelfABook")
    public ResponseEntity<Boolean> shelf(
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("isbn") String isbn,
            @RequestParam("description") String description,
            @RequestParam("coverUrl") String coverUrl,
            @RequestParam("latitude") Double latitude,
            @RequestParam("longitude") Double longitude,
            @RequestAttribute("userId") Long uploaderId
    ) {
        Book book = new Book();
        book.setUploaderId(uploaderId);
        book.setOwnerId(uploaderId);
        book.setLatitude(latitude);
        book.setLongitude(longitude);
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setStatus(BookStatus.AVAILABLE.getStatusString());
        book.setDescription(description);
        book.setCoverUrl(coverUrl);
        if (bookService.save(book)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
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
    public List<Book> searchByIsbn(@RequestParam(value = "isbn") String isbn) {
        return bookService.searchByIsbn(isbn);
    }

    @GetMapping("/search")
    public List<Book> search(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "exact", defaultValue = "false") boolean exact
    ) {
        return bookService.search(title, author, exact);
    }
}
