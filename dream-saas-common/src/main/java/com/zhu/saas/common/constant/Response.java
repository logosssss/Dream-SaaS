package com.zhu.saas.common.constant;

/**
 * ClassName:    Response
 * Package:    com.zhu.saas.common.constant
 * Description:
 * Datetime:    2026/3/18   15:55
 * Author:   朱殿辉
 */
public class Response<T> {
    public int code;
    public String message;
    public T data;

    public Response(ErrorCode errorCode) {
        this.code = 0;
        this.message = "";
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public Response(ErrorCode errorCode, T data) {
        this.code = 0;
        this.message = "";
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.data = data;
    }

    public Response(T data) {
        this.code = 0;
        this.message = "";
        this.data = data;
    }

    public Response() {
        this.code = 0;
        this.message = "";
    }

    public Response(int code) {
        this(code, ErrorCode.getMsg(code), (T) null);
    }

    public Response(int code, String message) {
        this(code, message, (T) null);
    }

    public Response(int code, String message, T data) {
        this.code = 0;
        this.message = "";
        this.code = code;
        this.message = message;
        this.data = (T) (data == null ? null : data);
    }

    public Response(int code, T data) {
        this(code, "", data);
    }

    public static <T> Response<T> error(int code) {
        return new Response<>(code);
    }

    public static <T> Response<T> error(String message) {
        return new Response<>(500, message);
    }

    public static <T> Response<T> error(ErrorCode errorCode, String message) {
        return new Response<>(errorCode.getCode(), message);
    }

    public static <T> Response<T> error(ErrorCode errorCode) {
        return new Response<>(errorCode.getCode(), errorCode.getMessage());
    }

    public static <T> Response<T> badRequest() {
        return new Response<>(400, "");
    }
}