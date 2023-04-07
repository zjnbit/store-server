package com.zjnbit.store.framework.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author chenjy
 * @emp chenjy 
 * @mobile 18158571991
 * @date 2021/11/29 10:34
 * @Description
 **/
@Data
@AllArgsConstructor
public class Result<T> {
    private String errCode;
    private String errMsg;
    private T data;

    private Object errData;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time = LocalDateTime.now();

    public Result() {
    }

    public Result(String errCode, String errMsg, T data, Object errData) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
        this.errData = errData;
    }

    public static <T> Result<T> success() {
        return success("00000", "操作成功", null);
    }

    public static <T> Result<T> success(T data) {
        return success("00000", "操作成功", data);
    }

    public static <T> Result<T> success(String errMsg, T data) {
        return success("00000", errMsg, data);
    }

    public static <T> Result<T> success(String errCode, String errMsg, T data) {
        return new Result<>(errCode, errMsg, data, null);
    }

}