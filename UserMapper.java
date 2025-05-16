// 用户数据访问层，负责用户表的数据库操作
package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户Mapper接口
 * 提供用户的增查操作
 */
@Mapper
public interface UserMapper {
    /**
     * 根据手机号或邮箱查找用户（用于注册唯一性校验）
     */
    User findByPhoneOrEmail(@Param("phone") String phone, @Param("email") String email);

    /**
     * 根据用户名/手机号/邮箱查找用户（用于登录）
     */
    User findByLogin(@Param("username") String username, @Param("phone") String phone, @Param("email") String email);

    /**
     * 新增用户
     */
    int insertUser(User user);
} 