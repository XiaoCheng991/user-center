package com.change.userCenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.change.userCenter.model.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
* @author cyq
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 确认密码
     * @return 新用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @return 返回脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     */
    User getSafetyUser(User originalUser);

    /**
     * 用户注销
     */
    int userLogout(HttpServletRequest request);
}
