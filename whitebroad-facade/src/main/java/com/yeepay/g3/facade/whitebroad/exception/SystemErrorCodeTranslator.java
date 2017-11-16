package com.yeepay.g3.facade.whitebroad.exception;

import com.yeepay.g3.facade.whitebroad.util.PropertyUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Description: 错误码转换
 * Author: jiawen.huang
 * Date: 2017/9/15
 * Time: 11:06
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class SystemErrorCodeTranslator {

	private static Log LOGGER = LogFactory.getLog(SystemErrorCodeTranslator.class);

	public static String getErrorCodeMessage(String errorCode) {
		try {
			return PropertyUtil.getInstance("error_code_msg").getProperty(errorCode);
		} catch (RuntimeException e) {
			throw new WbSysException("L10001", "系统异常");//properties 读取异常
		}
	}

	public static void main(String[] a) {
		System.out.print(SystemErrorCodeTranslator.getErrorCodeMessage("L10001"));
	}
}
