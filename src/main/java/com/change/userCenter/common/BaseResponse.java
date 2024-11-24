package com.change.userCenter.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 */
@Data
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = -71770533940771568L;

    /**
     * 通用返回状态码
     */
    private int code;

    /**
     * 通用返回消息
     */
    private String msg;

    /**
     * 通用返回数据
     */
    private T data;

    /**
     * 通用返回描述
     */
    public String description;


    public BaseResponse(int code) {
        this.code = code;
    }

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public BaseResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(int code, String msg, T data, String description) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.description = description;
    }

}
