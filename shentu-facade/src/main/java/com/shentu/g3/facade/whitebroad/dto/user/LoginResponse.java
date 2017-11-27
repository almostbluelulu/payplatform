package com.shentu.g3.facade.whitebroad.dto.user;

import com.shentu.g3.facade.whitebroad.dto.BaseResponse;
import com.shentu.g3.facade.whitebroad.enumtype.UserStatus;

/**
 * Description: 登陆响应
 * Author: jiawen.huang
 * Date: 16/11/14
 * Time: 15:35
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class LoginResponse extends BaseResponse {

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
	 * 状态
	 */
	private UserStatus userStatus;

	/**
	 * 登录token码
	 */
	private String token;

	/**
	 * 刷新时间
	 */
	private String refreshTime;

	/**
	 * 过期时间
	 */
	private String expireTime;

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

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(String refreshTime) {
		this.refreshTime = refreshTime;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
}
