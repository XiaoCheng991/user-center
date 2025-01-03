package com.change.userCenter.common;

/**
 * 通用错误码
 */
public enum ErrorCode {

    PARAMS_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40001, "请求数据为空", ""),
    NOT_LOGIN(40100, "未登录", ""),
    NO_AUTH(40101, "暂无权限", ""),
    BAN(40301, "账号已被封禁", ""),

    SYSTEM_ERROR(50000, "系统内部异常", "");

    /**
     * 错误码
     */
    private final int code;

    /**
     * 错误消息
     */
    private final String msg;

    /**
     * 错误描述
     */
    private final String description;

    ErrorCode(int code, String msg, String description) {
        this.code = code;
        this.msg = msg;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getDescription() {
        return description;
    }
}
