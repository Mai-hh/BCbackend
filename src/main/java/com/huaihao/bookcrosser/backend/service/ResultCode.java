package com.huaihao.bookcrosser.backend.service;

public enum ResultCode {
    OK(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401);

    private int value;

    ResultCode(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
