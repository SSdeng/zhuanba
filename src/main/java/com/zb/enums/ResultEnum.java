package com.zb.enums;

/**
 * @author dengzhijian
 * @version 1.0
 **/
public enum ResultEnum {
    // 数据操作错误定义
    SUCCESS(0, "成功!"), FAILURE(1, "失败！"), USER_NOT_LOGIN(2, "用户未登录"), USER_NOT_AUTH(3, "用户未授权");

    /** 错误码 */
    private final Integer code;

    /** 错误描述 */
    private final String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
