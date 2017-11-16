package com.yeepay.g3.facade.whitebroad.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.UUID;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 2017/9/15
 * Time: 10:53
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class WbSysException extends RuntimeException {

	private static Log LOGGER = LogFactory.getLog(WbSysException.class);

	private String id;
	private String message;
	private String defineCode;
	private String realClassName;

	public WbSysException(String defineCode) {
		this.initId();
		this.defineCode = defineCode;
		this.message = SystemErrorCodeTranslator.getErrorCodeMessage(defineCode);
	}

	public WbSysException(String defineCode, Throwable throwable) {
		this.initId();
		this.defineCode = defineCode;
		LOGGER.error("WbSysException info:", throwable);
		this.message = SystemErrorCodeTranslator.getErrorCodeMessage(defineCode);
	}

	/**
	 * 透传
	 *
	 * @param defineCode
	 * @param message
	 */
	public WbSysException(String defineCode, String message) {
		this.initId();
		this.defineCode = defineCode;
		this.message = message;
	}

	private void initId() {
		this.id = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
	}

	public String getId() {
		return id;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public String getDefineCode() {
		return defineCode;
	}

	public String getRealClassName() {
		return realClassName;
	}
}
