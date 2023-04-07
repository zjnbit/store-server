package com.zjnbit.store.framework.web.handle;

import com.zjnbit.store.framework.web.model.RequestError;
import com.zjnbit.store.framework.web.model.RequestException;
import com.zjnbit.store.framework.web.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/17 15:49
 * @Description
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({RequestException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result requestException(RequestException e) {
        return new Result(e.getCode(), e.getMessage(), null, null);
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result runtimeException(RuntimeException e) {
        log.error(e.getMessage(), e.getStackTrace());
        log.error(e.getMessage(), e.fillInStackTrace());
        return new Result(RequestError.B0500.getErrCode(), RequestError.B0500.getErrMsg(), null, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public Result exception(Exception e) {
        log.error(e.getMessage(), e.getStackTrace());
        log.error(e.getMessage(), e.fillInStackTrace());
        return new Result(RequestError.B0500.getErrCode(), RequestError.B0500.getErrMsg(), null, e.getMessage());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result missingServletRequestParameterException(MissingServletRequestParameterException e) {
        List<Map<String, Object>> fields = new ArrayList<>();
        Map<String, Object> field = new HashMap<>(16);
        field.put("field", e.getParameterName());
        field.put("message", "参数不能为空");
        fields.add(field);
        Map<String, Object> map = new HashMap<>(1);
        map.put("fieldError", fields);
        return new Result(RequestError.A0003.getErrCode(), RequestError.A0003.getErrMsg(), null, map);
    }

    /**
     * 验证异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<Map<String, Object>> fields = new ArrayList<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            Map<String, Object> field = new HashMap<>(16);
            field.put("field", error.getField());
            field.put("message", error.getDefaultMessage());
            fields.add(field);
        }
        Map<String, Object> map = new HashMap<>(1);
        map.put("fieldError", fields);
        return new Result(RequestError.A0003.getErrCode(), RequestError.A0003.getErrMsg(), null, map);
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result httpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new Result(RequestError.B0400.getErrCode(), RequestError.B0400.getErrMsg(), null, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return new Result(RequestError.B0405.getErrCode(), RequestError.B0405.getErrMsg(), null, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return new Result(RequestError.B0415.getErrCode(), RequestError.B0415.getErrMsg(), null, e.getMessage());
    }
}
