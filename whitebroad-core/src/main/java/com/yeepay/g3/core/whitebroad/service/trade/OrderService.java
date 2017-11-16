/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core.whitebroad.service.trade;

import com.yeepay.g3.core.whitebroad.entity.trade.Order;

import java.util.List;

/**
 * @ClassName: OrderService
 * @Description: OrderService
 * @author: dongxulu
 * @date: 17/9/18 下午6:46
 * @version: 1.0.0
 */
public interface OrderService {

    public Order insert(Order pojo);

    public int insertList(List<Order> pojos);

    public int update(Order pojo);

    public Order findbyID(String id);

	/**
	 * 根据商编与商户订单号查询订单
	 */
	public Order findbyRequestNo(String requestNo,String customerNumber);

    /**
     * 
     * @Description: 查询收款订单列表
     * @param @param requestDTO
     * @param @return 
     * @return List<Order> 返回类型 
     * @author yunpeng.pan
     * @date 2017年9月25日 上午11:16:06
     */
	public List<Order> selectReceiptList(String id, String startDate, String endDate, String orderStatus, String payType,
										 String userNumber, int fromIndex, int pageSize);

	/**
	 * 
	 * @Description: 查询订单总数
	 * @param @param id
	 * @param @param startDate
	 * @param @param endDate
	 * @param @param orderStatus
	 * @param @param payType
	 * @param @param userNumber
	 * @param @return 
	 * @return int 返回类型 
	 * @author yunpeng.pan
	 * @date 2017年9月25日 下午6:26:26
	 */
	public int getOrderTotalCount(String id, String startDate, String endDate, String orderStatus, String payType,
								  String userNumber);

	public long getSequnce();
	/**
	 * 根据唯一id查询订单
	 */
	Order findByYeepayId(String yeepayid);


}