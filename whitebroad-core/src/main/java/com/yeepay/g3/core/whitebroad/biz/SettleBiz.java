package com.yeepay.g3.core.whitebroad.biz;


import com.yeepay.g3.facade.whitebroad.dto.trade.SettleListRequest;
import com.yeepay.g3.facade.whitebroad.dto.trade.SettleListResponse;

/**
 * Description: 结算类业务
 * Author: jiawen.huang
 * Date: 2017/9/20
 * Time: 18:54
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface SettleBiz {

	/**
	 * 根据商户编号和结算出款日期查询当日结算(T1的和S0的)
	 *
	 * @param request
	 * @return
	 */
	SettleListResponse findCuzSettleByDate(SettleListRequest request);
}
