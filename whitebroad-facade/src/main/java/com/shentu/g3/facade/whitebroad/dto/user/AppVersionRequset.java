package com.shentu.g3.facade.whitebroad.dto.user;

import com.shentu.g3.facade.whitebroad.dto.BaseRequest;

/**
 * Description:
 * Author: wei.li
 * Date: 17/6/8
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class AppVersionRequset extends BaseRequest {

	/**
	 * 是否手动
	 * 1:是
	 * 0:否
	 */
	private String isManual = "0";

	public String getIsManual() {
		return isManual;
	}

	public void setIsManual(String isManual) {
		this.isManual = isManual;
	}
}
