package com.yeepay.g3.facade.whitebroad.dto;

/**
 * Description: 推送查询请求
 * Author: jiawen.huang
 * Date: 2017/10/18
 * Time: 18:58
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class PushListRequest extends PageBaseRequest {

	/**
	 * 消息类型
	 */
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
