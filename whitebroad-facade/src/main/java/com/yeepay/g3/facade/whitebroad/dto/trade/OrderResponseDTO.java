package com.yeepay.g3.facade.whitebroad.dto.trade;

import com.yeepay.g3.facade.whitebroad.dto.BaseResponse;

import java.util.List;

/**
 * 订单列表出参
 *
 * @author yunpeng.pan
 * @date 2017年9月18日 下午3:23:14
 */
public class OrderResponseDTO extends BaseResponse {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3887187935469213897L;

	/**
     * 交易笔数
     */
    private int tradeCount;
    
    /**
     * 总页数
     */
    private int totalPage;
    
    /**
     * 订单明细
     */
	private List<OrderInfoDTO> orderList;

	public int getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
	}

	public List<OrderInfoDTO> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderInfoDTO> orderList) {
		this.orderList = orderList;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
    
}