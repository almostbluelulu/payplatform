package com.shentu.g3.core.whitebroad.service;

import com.shentu.g3.facade.whitebroad.enumtype.SmsTypeEnum;

import java.util.List;

/**
 * Description: 通知短信service
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 14:49
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface NotifyService {

	/**
	 * 发送验证码短信
	 *
	 * @param phoneNo
	 * @param code
	 * @param smsTypeEnum
	 */
	void sendSmsRandom(String phoneNo, String code, SmsTypeEnum smsTypeEnum);

	/**
	 * 发送自定义短信
	 *
	 * @param phoneNo 手机号
	 * @param content 短信内容
	 */
	void sendCustomSMS(String phoneNo, String content);

	/**
	 * 群发自定义短信
	 *
	 * @param phoneNos 手机号们
	 * @param content  群发内容
	 */
	void sendCustomSMS(List<String> phoneNos, String content);
}
