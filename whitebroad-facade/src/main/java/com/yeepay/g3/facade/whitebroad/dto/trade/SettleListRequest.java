package com.yeepay.g3.facade.whitebroad.dto.trade;

import com.yeepay.g3.facade.whitebroad.dto.PageBaseRequest;

/**
 * Description: 结算查询
 * Author: jiawen.huang
 * Date: 2017/9/21
 * Time: 17:21
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class SettleListRequest extends PageBaseRequest {

	private String settleType;

	public String getSettleType() {
		return settleType;
	}

	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}
}
