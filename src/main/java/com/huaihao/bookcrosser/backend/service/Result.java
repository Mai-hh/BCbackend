package com.huaihao.bookcrosser.backend.service;

public class Result {
    // 状态码,如 200 表示成功,400 表示失败
    private int code;
    // 提示信息,如"操作成功"、"参数错误"等
    private String message;
    // 数据,可以是任意类型,如用户信息、列表数据等
    private Object data;

    // 私有化构造方法,禁止外部直接创建 Result 对象
    private Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 静态方法,用于创建成功的 Result 对象
    public static Result success() {
        return new Result(200, "操作成功", null);
    }

    public static Result success(String message) {
        return new Result(200, message, null);
    }

    public static Result success(Object data) {
        return new Result(200, "操作成功", data);
    }

    public static Result success(String message, Object data) {
        return new Result(200, message, data);
    }

    // 静态方法,用于创建失败的 Result 对象
    public static Result failed() {
        return new Result(400, "操作失败", null);
    }

    public static Result failed(String message) {
        return new Result(400, message, null);
    }

    // getter 和 setter 方法
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
