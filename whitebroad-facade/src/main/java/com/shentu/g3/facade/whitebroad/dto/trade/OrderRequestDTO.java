package com.shentu.g3.facade.whitebroad.dto.trade;

import com.shentu.g3.facade.whitebroad.dto.PageBaseRequest;

/**
 * 到账订单列表入参
 *
 * @author yunpeng.pan
 * @date 2017年9月18日 下午3:22:54
 */
public class OrderRequestDTO extends PageBaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7721803132628981934L;

	/**
	 * 支付方式- PayType
	 */
	private String payType;
	
	/**
	 * 订单状态- OrderStatus
	 */
	private String orderStatus;

	/**
	 * 订单号
	 */
	private String orderId;

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}