package com.change.userCenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 * @author cyq
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 790687473483804595L;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 校验密码
     */
    private String checkPassword;
}
