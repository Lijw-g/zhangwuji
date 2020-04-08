package com.zhangwuji.api.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    private Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> Response<T> success(int code) {
        return new Response<>(code, "success");
    }

    public static <T> Response<T> success(int code, T data) {
        return new Response<>(code, "success", data);
    }

    public static <T> Response<T> error(int code, String msg) {
        return new Response<>(code, msg);
    }

    public static <T> Response<T> error(int code, String msg, T data) {
        return new Response<>(code, msg, data);
    }
}
