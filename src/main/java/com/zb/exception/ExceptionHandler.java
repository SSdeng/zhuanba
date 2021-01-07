package com.zb.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zb.util.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理器
 * 
 * @author dengzhijian
 * @version 1.0
 **/
@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    /**
     * 处理自定义异常
     * 
     * @param e
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = MyException.class)
    public Result customerExceptionHandler(MyException e) {
        log.error("发生异常！原因是:{}", e.getMessage());
        return Result.error(e.getMsg());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
    public Result DaoExceptionHandler(DataIntegrityViolationException e) {
        log.error("发生异常！原因是:{}", e.getMessage());
        return Result.error("含有重复字段");
    }
}
