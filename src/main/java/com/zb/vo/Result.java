package com.zb.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    /** 状态码 */
    private Integer status;
    /** 返回信息 */
    private String result;
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
        return new Result(-1, msg, obj);
    }

    public static Result error(String msg) {
        return new Result(-1, msg, null);
    }
}
