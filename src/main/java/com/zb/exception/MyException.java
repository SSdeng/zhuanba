package com.zb.exception;

import com.zb.enums.ResultEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常类
 * 
 * @author dengzhijian
 * @version 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class MyException extends RuntimeException {
    private static final long serialVersionUID = -3483218647914294289L;
    private int code = -1;
    private String msg;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public MyException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
