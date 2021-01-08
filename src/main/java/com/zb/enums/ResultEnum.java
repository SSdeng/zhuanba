package com.zb.enums;

/**
 * @author dengzhijian
 * @version 1.0
 **/
public enum ResultEnum {
    // 数据操作错误定义
    SUCCESS(0, "成功!"), FAILURE(1, "失败！"), USER_NOT_LOGIN(2, "用户未登录"), USER_NOT_AUTH(3, "用户未授权"),
    BODY_NOT_MATCH(400, "请求的数据格式不符!"), SIGNATURE_NOT_MATCH(401, "请求的数字签名不匹配!"), NOT_FOUND(404, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"), SERVER_BUSY(503, "服务器正忙，请稍后再试!");

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
