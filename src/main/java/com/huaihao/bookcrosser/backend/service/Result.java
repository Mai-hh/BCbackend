package com.huaihao.bookcrosser.backend.service;

import lombok.Data;


public class Result {
    // 状态码,如 200 表示成功,400 表示失败
    private int code;
    // 提示信息,如"操作成功"、"参数错误"等
    private String message;
    // 数据,可以是任意类型,如用户信息、列表数据等
    private Object data;
    // JWT token,登录成功时使用
    private String token;

    // 私有化构造方法,禁止外部直接创建 Result 对象
    private Result(int code, String message, Object data, String token) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.token = token;
    }

    // 静态方法,用于创建成功的 Result 对象
    public static Result success() {
        return new Result(200, "操作成功", null, null);
    }

    public static Result success(String message) {
        return new Result(200, message, null, null);
    }

    public static Result success(Object data) {
        return new Result(200, "操作成功", data, null);
    }

    public static Result success(String message, Object data) {
        return new Result(200, message, data, null);
    }

    // 静态方法,用于创建登录成功的 Result 对象
    public static Result loginSuccess(String token) {
        return new Result(200, "登录成功", null, token);
    }

    // 静态方法,用于创建失败的 Result 对象
    public static Result failed() {
        return new Result(400, "操作失败", null, null);
    }

    public static Result failed(String message) {
        return new Result(400, message, null, null);
    }

    public static Result failed(int code, String message) {
        return new Result(code, message, null, null);
    }

    public boolean isSuccess() {
        return this.code == ResultCode.OK.value();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public String getToken() {
        return token;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
