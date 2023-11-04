package com.tang.wx.config;

import com.tang.wx.utils.CustomException;
import com.tang.wx.utils.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Object validExceptionHandler(Exception e) {
        log.error("异常捕获", e);
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException)e;
            return Res.error(exception.getBindingResult().getFieldError().getDefaultMessage());
        } else if (e instanceof CustomException) {
            CustomException exception = (CustomException)e;
            return Res.error(exception.getMsg());
        } else {
            return Res.error();
        }
    }
}