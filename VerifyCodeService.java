package com.example.service;

import com.example.util.EmailUtil;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class VerifyCodeService {
    private static class CodeInfo {
        String code;
        long timestamp;
        int count;
    }
    private final Map<String, CodeInfo> phoneMap = new HashMap<>();
    private final Map<String, CodeInfo> emailMap = new HashMap<>();
    public String sendPhoneCode(String phone) {
        if (!phone.matches("^1[3-9]\\d{9}$")) return "手机号格式不正确";
        long now = System.currentTimeMillis();
        CodeInfo info = phoneMap.getOrDefault(phone, new CodeInfo());
        if (!isToday(info.timestamp, now)) info.count = 0;
        if (info.count >= 5) return "今日发送次数已达上限";
        String code = String.format("%06d", (int)(Math.random() * 1000000));
        // TODO: 调用第三方短信服务发送 code
        info.code = code;
        info.timestamp = now;
        info.count++;
        phoneMap.put(phone, info);
        System.out.println("【调试用】短信验证码：" + code);
        return "验证码已发送";
    }
    public String sendEmailCode(String email) {
        if (!email.endsWith("@qq.com")) return "仅支持QQ邮箱";
        long now = System.currentTimeMillis();
        CodeInfo info = new CodeInfo();
        String code = String.format("%06d", (int)(Math.random() * 1000000));
        EmailUtil.send(email, code);
        info.code = code;
        info.timestamp = now;
        emailMap.put(email, info);
        System.out.println("【调试用】邮箱验证码：" + code);
        return "验证码已发送";
    }
    public boolean verifyPhoneCode(String phone, String code) {
        CodeInfo info = phoneMap.get(phone);
        return info != null && info.code.equals(code) && !isExpired(info.timestamp);
    }
    public boolean verifyEmailCode(String email, String code) {
        CodeInfo info = emailMap.get(email);
        return info != null && info.code.equals(code) && !isExpired(info.timestamp);
    }
    private boolean isExpired(long timestamp) {
        return System.currentTimeMillis() - timestamp > 5 * 60 * 1000;
    }
    private boolean isToday(long old, long now) {
        return (old / (1000 * 60 * 60 * 24)) == (now / (1000 * 60 * 60 * 24));
    }
} 