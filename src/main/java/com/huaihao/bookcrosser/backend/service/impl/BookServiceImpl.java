package com.huaihao.bookcrosser.backend.service.impl;

import com.huaihao.bookcrosser.backend.mbg.mapper.BookMapper;
import com.huaihao.bookcrosser.backend.mbg.mapper.CommentMapper;
import com.huaihao.bookcrosser.backend.mbg.mapper.DriftingMapper;
import com.huaihao.bookcrosser.backend.mbg.model.Book;
import com.huaihao.bookcrosser.backend.mbg.model.BookStatus;
import com.huaihao.bookcrosser.backend.mbg.model.Comment;
import com.huaihao.bookcrosser.backend.mbg.model.DriftingRecord;
import com.huaihao.bookcrosser.backend.service.BookService;
import com.huaihao.bookcrosser.backend.service.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private DriftingMapper driftingMapper;
    @Autowired
    private CommentMapper commentMapper;

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
    public Result updateCommon(
            Long id,
            String title,
            String author,
            String description,
            LocalDateTime updatedAt
    ) {
        if (bookMapper.updateCommon(id, title, author, description, updatedAt)) {
            return Result.success();
        } else {
            return Result.failed();
        }
    }

    @Override
    public Result delete(Long id, Long userId) {
        Book book = bookMapper.selectById(id);
        if (book == null) {
            return Result.failed("未知图书");
        }

        if (!book.getUploaderId().equals(userId)) {
            return Result.failed("你不是书主");
        }

        if (!book.getOwnerId().equals(userId)) {
            return Result.failed("你不拥有这本书，请先收漂");
        }

        List<Comment> comments = commentMapper.selectByBookId(id);
        List<DriftingRecord> records = driftingMapper.selectByBookId(id);


        if (comments != null) {
            for (Comment comment : comments) {
                commentMapper.deleteComment(comment.getId());
            }
        }

        if (records != null) {
            for (DriftingRecord record : records) {
                driftingMapper.deleteById(record.getId());
            }
        }

        if (bookMapper.deleteById(id)) {
            return Result.success("删除成功");
        } else {
            return Result.failed("删除失败");
        }
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
    public List<Book> search(String title, String author, boolean exact, Long userId) {
        List<Book> books = bookMapper.search(title, author, exact);
        List<DriftingRecord> records = driftingMapper.selectByRequesterId(userId);
        for (Book book : books) {
            for (DriftingRecord record : records) {
                if (book.getId().equals(record.getBookId())) {
                    book.setStatus(BookStatus.REQUESTED.getStatusString());
                }
            }
        }

        return books;
    }
}
