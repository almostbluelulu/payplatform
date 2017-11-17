package com.shentu.g3.facade.whitebroad.dto.trade;

import java.io.Serializable;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 2017/10/23
 * Time: 10:55
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class PaymentDTO implements Serializable {

	private static final long serialVersionUID = 2681412379049256898L;

	/**
	 * 易宝订单号
	 */
	private String yeepayOrderId;
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 商编
	 */
	private String customerNumber;
	/**
	 * 支付状态
	 */
	private String payStatus;
	/**
	 * 支付方式
	 */
	private String payType;
	/**
	 * 交易金额
	 */
	private String trxAmt;
	/**
	 * 实际支付金额
	 */
	private String realAmount;
	/**
	 * 手续费
	 */
	private String feeAmount;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 卡号
	 */
	private String cardNumber;
	/**
	 * 卡类型
	 */
	private String cardType;
	/**
	 * 交易类型
	 */
	private String trxType;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 完成时间
	 */
	private String completeTime;

	public String getYeepayOrderId() {
		return yeepayOrderId;
	}

	public void setYeepayOrderId(String yeepayOrderId) {
		this.yeepayOrderId = yeepayOrderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getTrxAmt() {
		return trxAmt;
	}

	public void setTrxAmt(String trxAmt) {
		this.trxAmt = trxAmt;
	}

	public String getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(String realAmount) {
		this.realAmount = realAmount;
	}

	public String getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(String feeAmount) {
		this.feeAmount = feeAmount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getTrxType() {
		return trxType;
	}

	public void setTrxType(String trxType) {
		this.trxType = trxType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}
}
