package com.yeepay.g3.facade.whitebroad.dto.user;


import com.yeepay.g3.facade.whitebroad.dto.BaseRequest;
import com.yeepay.g3.facade.whitebroad.util.HiddenCodeUtil;

/**
 * Description: 登录请求
 * Author: jiawen.huang
 * Date: 2017/9/19
 * Time: 10:22
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class LoginRequest extends BaseRequest {

	/**
	 * 登陆密码密文
	 */
	private String pwd;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("LoginRequest{");
		sb.append("phoneNumber='").append(HiddenCodeUtil.hiddenMobile(phoneNumber)).append('\'');
		sb.append("imei='").append(imei).append('\'');
		sb.append("versionId='").append(versionId).append('\'');
		sb.append("location='").append(location).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
