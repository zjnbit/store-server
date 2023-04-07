package com.zjnbit.store.framework.web.model;


/**
 * @auther: chenjy
 * @emp: chenjy 
 * @mobile: 18158571991
 * @date: 2021/11/11 19:37
 * @Description:
 **/
public class RequestException extends RuntimeException {
    private String code;
    private String message;

    public RequestException(BaseError errorEnum) {
        this.code = errorEnum.getErrCode();
        this.message = errorEnum.getErrMsg();
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
