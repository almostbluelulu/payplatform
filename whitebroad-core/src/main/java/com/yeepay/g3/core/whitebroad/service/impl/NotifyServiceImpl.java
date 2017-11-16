package com.yeepay.g3.core.whitebroad.service.impl;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.yeepay.g3.core.whitebroad.service.NotifyService;
import com.yeepay.g3.core.whitebroad.util.Constant;
import com.yeepay.g3.core.whitebroad.util.RemoteFacadeFactory;
import com.yeepay.g3.facade.whitebroad.enumtype.ExternalSystem;
import com.yeepay.g3.facade.whitebroad.enumtype.SmsTypeEnum;
import com.yeepay.g3.facade.whitebroad.exception.ErrorCode;
import com.yeepay.g3.facade.whitebroad.exception.WbSysException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description: 通知短信service
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 14:49
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class NotifyServiceImpl implements NotifyService {

	private static String SMS_RULE = "wb-sms-rule";

	public static void main(String[] a) {
		Map<String, Object> params = Maps.newHashMap();
		Map<String, Object> message = Maps.newHashMap();
		message.put("message", "hhhhh");
		params.put("notifyRule", SMS_RULE);
		params.put("recipients", "18519397782");
		params.put("content", new Gson().toJson(message));
		System.out.print(RemoteFacadeFactory.getYopService(ExternalSystem.NOTIFY, params));
	}

	@Override
	public void sendSmsRandom(String phoneNo, String code, SmsTypeEnum smsTypeEnum) {
		String content = "";
		if (smsTypeEnum.equals(SmsTypeEnum.REGISTER)) {
			content = "您的注册验证码为：" + code + "。此验证码只用于注册，有效期5分钟，请不要把验证码泄露给其他人。";
		} else {
			content = "您的验证码为：" + code + "。此验证码只用于找回密码，有效期5分钟，请不要把验证码泄露给其他人。";
		}
		sendCustomSMS(phoneNo, content);
	}

	@Override
	public void sendCustomSMS(String phoneNo, String content) {
		notify(phoneNo, content);
	}

	@Override
	public void sendCustomSMS(List<String> phoneNos, String content) {
		notify(StringUtils.join(phoneNos, ","), content);
	}

	private void notify(String recipients, String content) {
		Map<String, Object> params = Maps.newHashMap();
		Map<String, Object> message = Maps.newHashMap();
		message.put("message", content);
		params.put("notifyRule", SMS_RULE);
		params.put("recipients", recipients);
		params.put("content", new Gson().toJson(message));
		Map<String, String> resultMap = (Map<String, String>) RemoteFacadeFactory.getYopService(ExternalSystem.NOTIFY, params);
		if (!resultMap.get("result").equals("Dispatched") && Constant.IS_PRODUCTION_ENVI) {
			throw new WbSysException(ErrorCode.NOTIFY_UNKNOWN_EXCEPTION);
		}
	}
}
