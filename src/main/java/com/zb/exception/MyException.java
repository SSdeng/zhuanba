package com.zb.exception;

import lombok.Data;

/**
 * @author dengzhijian
 * @version 1.0
 **/
@Data
public class MyException extends RuntimeException {
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
