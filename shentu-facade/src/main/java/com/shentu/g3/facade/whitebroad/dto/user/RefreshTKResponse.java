package com.shentu.g3.facade.whitebroad.dto.user;

import com.shentu.g3.facade.whitebroad.dto.BaseResponse;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 17/1/11
 * Time: 11:23
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class RefreshTKResponse extends BaseResponse {

	/**
	 * 三代会员号
	 */
	private String userNumber;

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

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
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
