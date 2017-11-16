package com.yeepay.g3.core.whitebroad.facade.impl;

import com.yeepay.g3.facade.whitebroad.dto.trade.SettleListRequest;
import com.yeepay.g3.facade.whitebroad.dto.trade.SettleListResponse;
import com.yeepay.g3.facade.whitebroad.facade.WbSettleFacade;

/**
 * Description: 结算类业务
 * Author: jiawen.huang
 * Date: 2017/10/12
 * Time: 16:12
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class WbSettleFacadeImpl extends AbstractFacade implements WbSettleFacade {

	@Override
	public SettleListResponse findCuzSettleByDate(SettleListRequest request) {
		return settleBiz.findCuzSettleByDate(request);
	}
}
