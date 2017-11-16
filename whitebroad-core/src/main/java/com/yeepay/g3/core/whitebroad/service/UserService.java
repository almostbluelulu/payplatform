package com.yeepay.g3.core.whitebroad.service;

import com.yeepay.g3.core.whitebroad.entity.UserEntity;

import java.util.List;

/**
 * Description: 用户类服务层
 * Author: jiawen.huang
 * Date: 2017/9/18
 * Time: 20:05
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface UserService {

    /**
     * 创建用户
     *
     * @param phoneNumber
     * @return
     */
    UserEntity create(String phoneNumber);

    /**
     * 查询用户
     *
     * @param phoneNo
     * @return
     */
    UserEntity findByPhoneNo(String phoneNo);

    /**
     * 查询
     *
     * @param userNo
     * @return
     */
    UserEntity findByUserNo(String userNo);

    /**
     * 查询已注册成功用户
     *
     * @param userNo
     * @return
     */
    UserEntity findRegisterByUserNo(String userNo);

    /**
     * 查询已注册成功用户
     *
     * @param phoneNo
     * @return
     */
    UserEntity findRegisterByPhone(String phoneNo);

    /**
     * 查询注册未完成用户
     *
     * @param phoneNo
     * @return
     */
    UserEntity findUnRegisterByPhone(String phoneNo);

    /**
     * 检查pwd，pwd正确返回对象，错误抛异常
     *
     * @param phoneNo
     * @param pwd
     * @return
     */
    UserEntity checkPwd(String phoneNo, String pwd);

    /**
     * 更新
     *
     * @param userEntity
     * @return
     */
    UserEntity update(UserEntity userEntity);

    /**
     * list all user
     *
     * @return
     */
    List<UserEntity> list();
}
