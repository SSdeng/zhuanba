package com.zb.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author dengzhijian
 * @version 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class MyException extends RuntimeException {
    private static final long serialVersionUID = -3483218647914294289L;
    private int code = -1;
    private String msg;

    public MyException() {
        super();
    }

    public MyException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
