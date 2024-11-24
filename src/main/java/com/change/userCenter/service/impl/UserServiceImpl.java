package com.change.userCenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.change.userCenter.common.ErrorCode;
import com.change.userCenter.exception.BusinessException;
import com.change.userCenter.model.domain.User;
import com.change.userCenter.service.UserService;
import com.change.userCenter.mapper.UserMapper;
import com.change.userCenter.utils.Validator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.change.userCenter.constant.userConstant.USER_LOGIN_STATE;


/**
 * @author cyq
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 加密盐
     */
    private static final String SALT = "cyq";


    @Resource
    private UserMapper userMapper;

    /**
     * 用户注册
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 确认密码
     * @return 新用户id
     */
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1.校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        // 校验账户
        if (userAccount.length() < 6) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户长度不能小于6");
        }
        // 校验密码
        if (userPassword.length() < 6 || checkPassword.length() < 6) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度不能小于6");
        }
        // 不能包含特殊字符
        if (!Validator.isUsername(userAccount)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户不能包含特殊字符");
        }
        // 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码不正确");
        }
        // 账户不能重复,放在最后优化接口性能
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("userAccount", userAccount);
        int count = this.count(qw);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户已存在");
        }

        // 2.加密密码
        String handlePassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 插入数据
        User user = new User(userAccount, handlePassword);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "注册失败");
        }

        return user.getId();
    }

    /**
     * 用户登录
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @return 返回脱敏后的用户信息
     */
    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1.校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        // 校验账户/密码
        if (userAccount.length() < 6 || userPassword.length() < 6) {
            return null;
        }
        // 不能包含特殊字符
        if (!Validator.isUsername(userAccount)){
            return null;
        }

        // 2.加密密码
        String handlePassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 账户不能重复,放在最后优化接口性能
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("userAccount", userAccount);
        qw.eq("userPassword", handlePassword);
        User user = userMapper.selectOne(qw);
        if (user == null) {
            log.info("user login failed cannot match userPassword");
            return null;
        }

        // 3. 用户脱敏
        User safetyUser = getSafetyUser(user);

        // 4.记录用户登陆状态
        request.getSession().setAttribute(USER_LOGIN_STATE, user);

        return safetyUser;
    }

    /**
     * 用户脱敏
     */
    @Override
    public User getSafetyUser(User originalUser) {
        if (originalUser == null) {
            return null;
        }
        // 脱敏，不反悔如密码等重要字段
        User safetyUser = new User();
        safetyUser.setId(originalUser.getId());
        safetyUser.setUserAccount(originalUser.getUserAccount());
        safetyUser.setUserRole(originalUser.getUserRole());
        safetyUser.setUserName(originalUser.getUserName());
        safetyUser.setAvatarUrl(originalUser.getAvatarUrl());
        safetyUser.setGender(originalUser.getGender());
        safetyUser.setPhone(originalUser.getPhone());
        safetyUser.setEmail(originalUser.getEmail());
        safetyUser.setUserStatus(originalUser.getUserStatus());
        safetyUser.setCreateTime(originalUser.getCreateTime());
        return safetyUser;
    }

    /**
     * 用户注销
     */
    @Override
    public int userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }
}




