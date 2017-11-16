package com.yeepay.g3.core.whitebroad.entity;

import com.yeepay.g3.facade.whitebroad.enumtype.UserStatus;

/**
 * Description: 用户表
 * Author: jiawen.huang
 * Date: 17/09/18
 * Time: 16:14
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class UserEntity extends IdEntity {

	/**
	 * 手机号
	 */
	private String phoneNumber;

	/**
	 * 用户编号
	 */
	private String userNumber;

	/**
	 * 所属商户
	 */
	private String customerNumber;

	/**
	 * 角色
	 */
	private Long roleId;

	/**
	 * 状态
	 */
	private UserStatus userStatus;

	/**
	 * 密码
	 */
	private String userPwd;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
}
