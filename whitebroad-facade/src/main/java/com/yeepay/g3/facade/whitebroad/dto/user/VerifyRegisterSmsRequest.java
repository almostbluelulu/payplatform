package com.yeepay.g3.facade.whitebroad.dto.user;

import com.yeepay.g3.facade.whitebroad.dto.BaseRequest;
import com.yeepay.g3.facade.whitebroad.util.HiddenCodeUtil;

/**
 * Description: 验证短信验证码请求类
 * Author: jiawen.huang
 * Date: 2017/9/19
 * Time: 10:22
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class VerifyRegisterSmsRequest extends BaseRequest {

	/**
	 * 密码密文
	 */
	private String pwd;

	/**
	 * 短信验证码
	 */
	private String smsCode;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("VerifyRegisterSmsRequest{");
		sb.append("phoneNumber='").append(HiddenCodeUtil.hiddenMobile(phoneNumber)).append('\'');
		sb.append("versionId='").append(versionId).append('\'');
		sb.append("imei='").append(imei).append('\'');
		sb.append("location='").append(HiddenCodeUtil.hiddenMobile(location)).append('\'');
		sb.append("smsCode='").append(HiddenCodeUtil.hiddenVerifyCode(smsCode)).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
