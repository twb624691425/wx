package com.tang.wx.utils;

import lombok.Data;
import org.apache.http.HttpStatus;

@Data
public class CustomException extends RuntimeException {
    private String msg;
    private int code = HttpStatus.SC_INTERNAL_SERVER_ERROR;

    public CustomException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public CustomException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public CustomException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public CustomException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
