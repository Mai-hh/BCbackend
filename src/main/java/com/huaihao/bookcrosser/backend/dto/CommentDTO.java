package com.huaihao.bookcrosser.backend.dto;

import com.huaihao.bookcrosser.backend.mbg.model.Book;
import com.huaihao.bookcrosser.backend.mbg.model.Comment;
import com.huaihao.bookcrosser.backend.mbg.model.User;

public class CommentDTO {
    private Comment comment;
    private User sender;
    private Book book;

    public CommentDTO() {}

    public CommentDTO(Comment comment, User sender, Book book) {
        this.comment = comment;
        this.sender = sender;
        this.book = book;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
