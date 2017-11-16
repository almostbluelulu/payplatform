package com.yeepay.g3.core.whitebroad.entity.order;

import com.yeepay.g3.core.whitebroad.entity.IdEntity;
import com.yeepay.g3.facade.whitebroad.enumtype.SettleTypeEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description: 结算表
 * Author: jiawen.huang
 * Date: 2017/9/19
 * Time: 20:58
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class SettleEntity extends IdEntity {

	/**
	 * 商户
	 */
	private String customerNumber;

	/**
	 * 结算金额
	 */
	private BigDecimal settleAmount;

	/**
	 * 手续费
	 */
	private BigDecimal settleFee;

	/**
	 * 结算类型
	 */
	private SettleTypeEnum settleType;

	/**
	 * 出款日期 jdbcType=Date
	 */
	private Date settleDate;

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public BigDecimal getSettleAmount() {
		return settleAmount;
	}

	public void setSettleAmount(BigDecimal settleAmount) {
		this.settleAmount = settleAmount;
	}

	public SettleTypeEnum getSettleType() {
		return settleType;
	}

	public BigDecimal getSettleFee() {
		return settleFee;
	}

	public void setSettleFee(BigDecimal settleFee) {
		this.settleFee = settleFee;
	}

	public void setSettleType(SettleTypeEnum settleType) {
		this.settleType = settleType;
	}

	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}
}
