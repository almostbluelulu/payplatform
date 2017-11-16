package com.yeepay.g3.core.whitebroad.facade.impl;

import com.yeepay.g3.core.whitebroad.biz.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description: 注入biz
 * Author: jiawen.huang
 * Date: 2017/9/20
 * Time: 19:05
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class AbstractFacade {

	@Autowired
	protected WbUserAuthBiz wbUserAuthBiz;

	@Autowired
	protected WbApiInfoBiz wbApiInfoBiz;

	@Autowired
	protected ApplyBiz applyBiz;

	@Autowired
	protected WbUserBiz wbUserBiz;

	@Autowired
	protected TradeServiceBiz tradeServiceBiz;

	@Autowired
	protected SettleBiz settleBiz;

	@Autowired
	protected AppVersionBiz appVersionBiz;

	@Autowired
	protected PushMsgBiz pushMsgBiz;
}
