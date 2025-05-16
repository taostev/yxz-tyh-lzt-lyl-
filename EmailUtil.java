package com.example.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailUtil {
    public static void send(String to, String code) {
        // QQ邮箱SMTP配置
        String host = "smtp.qq.com";
        String from = "1641819932@qq.com";
        String pass = "sbjkozhqrbgbedbf"; // 不是QQ密码，是SMTP授权码
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("注册验证码");
            message.setText("您的注册验证码为：" + code + "，5分钟内有效。");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
} 