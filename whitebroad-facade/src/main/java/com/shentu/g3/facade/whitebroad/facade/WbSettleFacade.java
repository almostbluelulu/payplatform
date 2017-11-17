package com.shentu.g3.facade.whitebroad.facade;

import com.shentu.g3.facade.whitebroad.dto.trade.SettleListResponse;
import com.shentu.g3.facade.whitebroad.dto.trade.SettleListRequest;

/**
 * Description: 结算类业务
 * Author: jiawen.huang
 * Date: 2017/10/12
 * Time: 16:12
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface WbSettleFacade {

	/**
	 * 根据商户编号和结算出款日期查询当日结算(T1的和S0的)
	 *
	 * @param request
	 * @return
	 */
	SettleListResponse findCuzSettleByDate(SettleListRequest request);
}
