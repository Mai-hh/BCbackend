package com.huaihao.bookcrosser.backend.mbg.model;

public enum BookStatus {
    AVAILABLE("available"),
    BORROWED("borrowed"),
    REQUESTED("requested"),
    UNAVAILABLE("unavailable");

    private final String statusString;

    BookStatus(String statusString) {
        this.statusString = statusString;
    }

    public String getStatusString() {
        return statusString;
    }
}
