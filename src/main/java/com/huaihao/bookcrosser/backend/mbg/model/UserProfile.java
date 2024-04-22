package com.huaihao.bookcrosser.backend.mbg.model;


import java.util.List;

public class UserProfile {
    private String username;
    private String email;
    private String avatar;
    private String bio;
    private Double latitude;
    private Double longitude;
    private List<Book> booksUploaded;
    private List<Book> booksBorrowed;
    private List<Book> booksInRequesting;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Book> getBooksUploaded() {
        return booksUploaded;
    }

    public void setBooksUploaded(List<Book> booksUploaded) {
        this.booksUploaded = booksUploaded;
    }

    public List<Book> getBooksBorrowed() {
        return booksBorrowed;
    }

    public void setBooksBorrowed(List<Book> booksBorrowed) {
        this.booksBorrowed = booksBorrowed;
    }

    public List<Book> getBooksInRequesting() {
        return booksInRequesting;
    }

    public void setBooksInRequesting(List<Book> booksInRequesting) {
        this.booksInRequesting = booksInRequesting;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
