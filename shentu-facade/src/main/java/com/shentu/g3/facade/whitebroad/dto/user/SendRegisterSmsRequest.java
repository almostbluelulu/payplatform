package com.shentu.g3.facade.whitebroad.dto.user;

import com.shentu.g3.facade.whitebroad.dto.BaseRequest;
import com.shentu.g3.facade.whitebroad.util.HiddenCodeUtil;

/**
 * Description: 发送短信验证码请求类
 * Author: jiawen.huang
 * Date: 2017/9/19
 * Time: 10:22
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class SendRegisterSmsRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7769319425124976891L;

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("SendRegisterSmsRequest{");
		sb.append("requestNo='").append(requestNo).append('\'');
		sb.append("phoneNumber='").append(HiddenCodeUtil.hiddenMobile(phoneNumber)).append('\'');
		sb.append("versionId='").append(versionId).append('\'');
		sb.append("imei='").append(imei).append('\'');
		sb.append("location='").append(HiddenCodeUtil.hiddenMobile(location)).append('\'');
		sb.append('}');
		return sb.toString();
	}
}