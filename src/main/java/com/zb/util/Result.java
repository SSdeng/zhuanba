package com.zb.util;

import com.zb.enums.ResultEnum;

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
    private Object data;

    public static Result build(ResultEnum resultEnum, Object obj) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        result.setData(obj);
        return result;
    }

    public static Result build(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

    public static Result ok(Object obj) {
        return build(ResultEnum.SUCCESS, obj);
    }

    public static Result ok() {
        return build(ResultEnum.SUCCESS);
    }

    public static Result error(Object obj) {
        return build(ResultEnum.FAILURE, obj);
    }

    public static Result error() {
        return build(ResultEnum.FAILURE);
    }
}
