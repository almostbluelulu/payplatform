package com.shentu.g3.app.pay.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.UUID;

/**
 * Description: 异常
 * Author: jiawen.huang
 * Date: 2017/9/15
 * Time: 10:53
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class WFSysException extends RuntimeException {

	private static Log LOGGER = LogFactory.getLog(WFSysException.class);

	private String id;
	private String message;
	private String defineCode;

	public WFSysException(String defineCode, String message) {
		this.initId();
		this.message = message;
		this.defineCode = defineCode;
	}

	public WFSysException(String defineCode, String message, Throwable throwable) {
		this.initId();
		this.defineCode = defineCode;
		this.message = message;
		LOGGER.error("WbSysException info:", throwable);
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
}
