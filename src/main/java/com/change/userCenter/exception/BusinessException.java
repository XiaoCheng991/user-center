package com.change.userCenter.exception;


import com.change.userCenter.common.ErrorCode;
import lombok.Getter;

/**
 * 自定义异常类
 */
@Getter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -821439514791346320L;

    /**
     * 错误码
     */
    private final int code;

    /**
     * 错误描述
     */
    private final String description;


    public BusinessException(int code, String msg, String description) {
        super(msg);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.description = description;
    }

}
