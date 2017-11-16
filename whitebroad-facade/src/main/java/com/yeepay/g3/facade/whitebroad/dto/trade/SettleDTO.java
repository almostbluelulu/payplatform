package com.yeepay.g3.facade.whitebroad.dto.trade;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Description: 结算单笔DTO
 * Author: jiawen.huang
 * Date: 2017/9/21
 * Time: 17:22
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class SettleDTO implements Serializable {

	private static final long serialVersionUID = 3327365119392532014L;

	/**
	 * 结算金额
	 */
	private String settleAmount;

	/**
	 * 手续费
	 */
	private String settleFee;

	/**
	 * 结算类型
	 */
	private String settleType;

	/**
	 * 出款日期 yyyy-MM-dd
	 */
	private String settleDate;

	public String getSettleAmount() {
		return settleAmount;
	}

	public void setSettleAmount(String settleAmount) {
		this.settleAmount = settleAmount;
	}

	public String getSettleFee() {
		return settleFee;
	}

	public void setSettleFee(String settleFee) {
		this.settleFee = settleFee;
	}

	public String getSettleType() {
		return settleType;
	}

	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
