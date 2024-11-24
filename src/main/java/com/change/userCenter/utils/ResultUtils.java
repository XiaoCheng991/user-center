package com.change.userCenter.utils;

import com.change.userCenter.common.BaseResponse;
import com.change.userCenter.common.ErrorCode;

/**
 * 返回工具类
 */
public class ResultUtils {

    /**
     * 成功
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(200, "ok", data);
    }

    /**
     * 失败
     */
    public static <T> BaseResponse<T> error(T data) {
        return new BaseResponse<>(500, "error", data);
    }

    /**
     * 失败
     */
    public static BaseResponse<String> error(int code, String msg) {
        return new BaseResponse(code, msg);
    }

    /**
     * 失败
     */
    public static BaseResponse<String> error(int code, String msg, String description) {
        return new BaseResponse(code, msg, null, description);
    }

    /**
     * 失败
     */
    public static  BaseResponse error(ErrorCode errorCode, String msg) {
        return new BaseResponse(errorCode.getCode(), msg, null);
    }

    /**
     * 失败
     */
    public static BaseResponse error(ErrorCode errorCode, String msg, String description) {
        return new BaseResponse(errorCode.getCode(), msg, null, description);
    }

}
