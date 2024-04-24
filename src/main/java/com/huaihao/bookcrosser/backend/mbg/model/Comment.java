package com.huaihao.bookcrosser.backend.mbg.model;

import java.time.LocalDateTime;

public class Comment {

    private Long id;
    private Long bookId;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Comment(Long userId, Long bookId, String content) {
        this.userId = userId;
        this.bookId = bookId;
        this.content = content;
    }

    public Comment(Long id, Long userId, Long bookId, String content) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.content = content;
    }

    public Comment(Long id, Long userId, Long bookId, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Comment(Long id, Long userId, Long bookId, String content, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
