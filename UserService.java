// 用户服务层，处理用户注册和登录的业务逻辑
// 用于二手书交易平台的用户管理业务
package com.example.service;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 用户服务类
 * 实现用户注册、登录等核心业务逻辑
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerifyCodeService verifyCodeService;

    /**
     * 用户注册逻辑
     * @param user 用户注册信息
     * @param code 验证码
     * @param mode 验证方式（phone或email）
     * @return 注册是否成功
     */
    public boolean register(User user, String code, String mode) {
        if ("phone".equals(mode)) {
            if (!verifyCodeService.verifyPhoneCode(user.getPhone(), code)) return false;
        } else if ("email".equals(mode)) {
            if (!verifyCodeService.verifyEmailCode(user.getEmail(), code)) return false;
        }
        // 校验手机号/邮箱唯一性
        User exist = userMapper.findByPhoneOrEmail(user.getPhone(), user.getEmail());
        if (exist != null) return false;
        // 密码加密
        String hash = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(hash);
        // 默认角色
        user.setRole("user");
        // 保存用户
        return userMapper.insertUser(user) > 0;
    }

    /**
     * 用户登录逻辑
     * @param user 用户登录信息
     * @return 登录成功后生成的JWT token
     */
    public String login(User user) {
        // 校验用户名/手机号/邮箱+密码，生成JWT token
        return "token:xxx";
    }
} 