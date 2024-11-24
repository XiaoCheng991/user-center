package com.change.userCenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 程永强
 */
@SpringBootApplication
@MapperScan("com.change.userCenter.mapper")
public class UserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class, args);
        System.out.println("\n ε=ε=ε=(~￣▽￣)~  用户中心管理系统启动成功  (´･ω･`)? \n");
    }

}
