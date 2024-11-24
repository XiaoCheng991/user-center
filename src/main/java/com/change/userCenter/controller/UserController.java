package com.change.userCenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.change.userCenter.common.BaseResponse;
import com.change.userCenter.common.ErrorCode;
import com.change.userCenter.exception.BusinessException;
import com.change.userCenter.model.domain.User;
import com.change.userCenter.model.request.UserLoginRequest;
import com.change.userCenter.model.request.UserRegisterRequest;
import com.change.userCenter.service.UserService;
import com.change.userCenter.utils.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.change.userCenter.constant.userConstant.ADMIN_ROLE;
import static com.change.userCenter.constant.userConstant.USER_LOGIN_STATE;


/**
 * 用户控制器
 * @author xiaoCheng
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @return 新用户id
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        long res = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(res);
    }


    /**
     * 用户登录
     * @return 返回脱敏后的用户信息
     */
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    /**
     * 用户注销
     */
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        return ResultUtils.success(userService.userLogout(request));
    }

    /**
     * 获取用户的登录态
     */
    @GetMapping("/currentUser")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if (currentUser == null) {
            return null;
        }
        // 根据 userId 查询数据库最新的数据, 校验用户是否合法，状态是否被封号
        User user = userService.getById(currentUser.getId());
        if (user.getUserStatus() == 1) {
            throw new BusinessException(ErrorCode.BAN);
        }
        return ResultUtils.success(userService.getSafetyUser(user));
    }


    /**
     * 搜索用户
     * @param userName 用户名
     * @return 用户列表
     */
    @GetMapping("/search")
    public BaseResponse<List<User>> searchUserList(@RequestParam(value = "userName", required = false) String userName, HttpServletRequest request) {
        Boolean isAdmin = isAdmin(request);
        if (!isAdmin) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.like("userName", userName);
        }
        List<User> list = userService.list(queryWrapper);
        List<User> userList = list.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(userList);
    }


    /**
     * 删除用户
     * @param userId 用户id
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestParam("userId") Long userId, HttpServletRequest request) {
        Boolean isAdmin = isAdmin(request);
        if (!isAdmin || userId <= 0) {
            return ResultUtils.error(false);
        }
        return ResultUtils.success(userService.removeById(userId));
    }


    /**
     * 鉴权
     */
    private static Boolean isAdmin(HttpServletRequest request) {
        // 鉴权，仅管理员可以查询
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }
}
