package com.zb.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于向前端传输操作结果响应
 * 
 * @author dengzhijian
 * @version 1.1
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Result {
    /** 状态码 */
    private Integer code;
    /** 返回信息 */
    private String msg;
    /** 数据 */
    private Object obj;

    public static Result build() {
        return new Result();
    }

    public static Result ok(String msg, Object obj) {
        return new Result(0, msg, obj);
    }

    public static Result ok(String msg) {
        return new Result(0, msg, null);
    }

    public static Result error(String msg, Object obj) {
        return new Result(1, msg, obj);
    }

    public static Result error(String msg) {
        return new Result(1, msg, null);
    }
}
