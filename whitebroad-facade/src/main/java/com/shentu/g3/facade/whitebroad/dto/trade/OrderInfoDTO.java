package com.shentu.g3.facade.whitebroad.dto.trade;

import com.shentu.g3.facade.whitebroad.dto.BaseResponse;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 订单列表出参
 *
 * @author yunpeng.pan
 * @date 2017年9月18日 下午3:23:14
 */
public class OrderInfoDTO extends BaseResponse {

	private static final long serialVersionUID = 3327365119392532014L;

	/**
     *系统流水 唯一
     */
    private String id;
    
    /**
     * 商编
     */
    private String customerNumber;
    
    /**
     * 商户订单号
     */
    private String requestNo;
    
    /**
     * 易宝订单号
     */
    private String yeepayOrderId;
    
    /**
     * 下单用户
     */
    private String userNumber;
    
    /**
     * 订单token 存储订单处理器下单token
     */
    private String token;

    /**
     * 支付类型
     */
    private String payType;
    
    /**
     * 二维码编号
     */
    private String qrID;
    
    /**
     * 到账类型
     */
    private String orderType;
 
    /**
     * 订单状态
     */
    private String orderStatus;
    
    /**
     * 交易金额
     */
    private String trxAmt;
    
    /**
     *实际支付金额
     */
    private String realAmount;
    
    /**
     * 手续费
     */
    private String feeAmount;
    
    /**
     *完成时间
     */
    private String completeTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String getYeepayOrderId() {
		return yeepayOrderId;
	}

	public void setYeepayOrderId(String yeepayOrderId) {
		this.yeepayOrderId = yeepayOrderId;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getQrID() {
		return qrID;
	}

	public void setQrID(String qrID) {
		this.qrID = qrID;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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

	public String getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}