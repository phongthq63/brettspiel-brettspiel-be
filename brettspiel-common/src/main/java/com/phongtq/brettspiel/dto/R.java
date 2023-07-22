package com.phongtq.brettspiel.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;

/**
 * Created by Quach Thanh Phong
 * On 7/5/2023 - 10:22 AM
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R<T> implements Serializable {

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private T data;

    @Getter
    private long ts = Instant.now().toEpochMilli();


    public static <T> R<T> ok() {
        return restResult(null, 0, null);
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, 0, null);
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, 0, msg);
    }

    public static <T> R<T> ok(String msg) {
        return restResult(null, 0, msg);
    }

    public static <T> R<T> failed() {
        return restResult(null, -1, null);
    }

    public static <T> R<T> failed(String msg) {
        return restResult(null, -1, msg);
    }

    public static <T> R<T> failed(Integer code, T data, String msg) {
        return restResult(data, code, msg);
    }

    public static <T> R<T> failed(Integer code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> R<T> failed(T data) {
        return restResult(data, -1, null);
    }

    public static <T> R<T> failed(T data, String msg) {
        return restResult(data, -1, msg);
    }

    public static <T> R<T> ok(Integer code, T data, String msg) {
        return restResult(data, code, msg);
    }

    public static <T> R<T> ok(Integer code, T data) {
        return restResult(data, code, null);
    }

    public static <T> R<T> ok(Integer code, String msg) {
        return restResult(null, code, msg);
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

}
