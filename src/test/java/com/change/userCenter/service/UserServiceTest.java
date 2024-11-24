package com.change.userCenter.service;

import com.change.userCenter.model.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUserName("yqcheng");
        user.setUserAccount("123");
        user.setAvatarUrl("https://c-ssl.duitang.com/uploads/blog/202306/14/xDS5D90Nc2G7z3a.jpg");
        user.setGender(0);
        user.setPhone("12345678901");
        user.setEmail("12345678901");
        user.setUserPassword("xxx");

        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assert.assertTrue(result);
    }


    @Test
    public void userRegister() {
        // 1.密码
        String userAccount = "yqcheng";
        String userPassword = "";
        String checkPassword = "123456";
        long res = userService.userRegister(userAccount, userPassword, checkPassword);
        Assert.assertNotEquals(1, res);

        // 2.账户长度小于八位
        userAccount = "yu";
        res = userService.userRegister(userAccount, userPassword, checkPassword);
        Assert.assertNotEquals(1, res);

        // 3.密码长度小于八位
        userAccount = "cyq";
        checkPassword = "12345678";
        res = userService.userRegister(userAccount, userPassword, checkPassword);
        Assert.assertNotEquals(1, res);

        // 4.特殊字符
        userAccount = "c yqQ";
        userPassword = "12345678";
        res = userService.userRegister(userAccount, userPassword, checkPassword);
        Assert.assertNotEquals(1, res);

        // 5.密码是否正确
        userAccount = "cyqQ";
        checkPassword = "1234567890";
        res = userService.userRegister(userAccount, userPassword, checkPassword);
        Assert.assertNotEquals(1, res);

        // 6.账户是否重复
        userAccount = "yqcheng";
        checkPassword = "12345678";
        res = userService.userRegister(userAccount, userPassword, checkPassword);
        Assert.assertNotEquals(1, res);

        // 7.正常注册
        userAccount = "dogyupi6";
        res = userService.userRegister(userAccount, userPassword, checkPassword);
        Assert.assertTrue(res > 0);

    }
}