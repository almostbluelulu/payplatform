package com.yeepay.g3.facade.whitebroad.dto.trade;

import com.yeepay.g3.facade.whitebroad.dto.BaseResponse;

import java.util.List;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 2017/10/12
 * Time: 11:06
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class SettleListResponse extends BaseResponse {

	private static final long serialVersionUID = 3887187935469213897L;

	/**
	 * 笔数
	 */
	private int settleCount;

	/**
	 * 总额
	 */
	private String settleSum;

	/**
	 * 总页数
	 */
	private int totalPage;

	/**
	 * 明细
	 */
	private List<SettleDTO> settleList;

	public String getSettleSum() {
		return settleSum;
	}

	public void setSettleSum(String settleSum) {
		this.settleSum = settleSum;
	}

	public int getSettleCount() {
		return settleCount;
	}

	public void setSettleCount(int settleCount) {
		this.settleCount = settleCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<SettleDTO> getSettleList() {
		return settleList;
	}

	public void setSettleList(List<SettleDTO> settleList) {
		this.settleList = settleList;
	}
}
