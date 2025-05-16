// 用户控制器，提供用户注册和登录相关接口
// 用于二手书交易平台的用户管理API
package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户管理控制器
 * 提供注册、登录等RESTful接口
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VerifyCodeService verifyCodeService;

    /**
     * 用户注册接口
     * @param body 包含注册信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public String register(@RequestBody Map<String, Object> body) {
        String mode = (String) body.get("mode");
        String code = (String) body.get("code");
        String password = (String) body.get("password");
        String phone = (String) body.get("phone");
        String email = (String) body.get("email");
        User user = new User();
        user.setPassword(password);
        user.setPhone(phone);
        user.setEmail(email);
        boolean success = userService.register(user, code, mode);
        return success ? "注册成功" : "验证码错误或用户已存在";
    }

    /**
     * 用户登录接口
     * @param user 用户登录信息
     * @return 登录结果（应返回JWT token）
     */
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        String token = userService.login(user);
        return token != null ? "登录成功，token:" + token : "用户名或密码错误";
    }

    @PostMapping("/sendPhoneCode")
    public String sendPhoneCode(@RequestBody Map<String, String> body) {
        String phone = body.get("phone");
        return verifyCodeService.sendPhoneCode(phone);
    }

    @PostMapping("/sendEmailCode")
    public String sendEmailCode(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        return verifyCodeService.sendEmailCode(email);
    }
} 