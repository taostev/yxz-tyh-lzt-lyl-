// 用户实体类，包含注册、登录、认证等相关字段
// 用于二手书交易平台的用户管理
package com.example.entity;

import java.util.Date;

/**
 * 用户实体类
 * 包含用户的基本信息、认证信息、权限信息等
 */
public class User {
    /** 用户ID */
    private Long id;
    /** 用户名 */
    private String username;
    /** 密码（加密存储） */
    private String password;
    /** 手机号 */
    private String phone;
    /** 邮箱 */
    private String email;
    /** 微信openid */
    private String wxOpenid;
    /** QQ openid */
    private String qqOpenid;
    /** 真实姓名 */
    private String realName;
    /** 学号 */
    private String studentId;
    /** 认证类型（学信网/一卡通） */
    private String authType;
    /** 认证状态（0未认证 1认证中 2已认证 3认证失败） */
    private Integer authStatus;
    /** 角色（user/admin） */
    private String role;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 学校ID */
    private Integer schoolId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public Integer getSchoolId() { return schoolId; }
    public void setSchoolId(Integer schoolId) { this.schoolId = schoolId; }
    // getter/setter 省略
} 