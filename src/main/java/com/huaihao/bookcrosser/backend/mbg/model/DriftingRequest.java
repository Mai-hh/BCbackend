package com.huaihao.bookcrosser.backend.mbg.model;

public class DriftingRequest {
    private Long id;
    private User requester;
    private Book book;

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
