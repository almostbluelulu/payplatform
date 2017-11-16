package com.yeepay.g3.core.whitebroad.biz;

import com.yeepay.g3.core.whitebroad.service.*;
import com.yeepay.g3.core.whitebroad.service.order.SettleService;
import com.yeepay.g3.core.whitebroad.service.qrcode.QrCodeService;
import com.yeepay.g3.core.whitebroad.service.trade.OrderService;
import com.yeepay.g3.core.whitebroad.service.trade.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description: 注入biz里要用的service
 * Author: jiawen.huang
 * Date: 2017/9/19
 * Time: 10:15
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class AbstractBiz {

	@Autowired
	protected AppVersionService appVersionService;

	@Autowired
	protected UserService userService;

	@Autowired
	protected SmsCodeService smsCodeService;

	@Autowired
	protected SecurityControlService securityControlService;

	@Autowired
	protected WbUserTokenService wbUserTokenService;

	@Autowired
	protected WbApiConfigService wbApiConfigService;

	@Autowired
	protected NotifyService notifyService;

	@Autowired
	protected OrderService orderService;

	@Autowired
	protected PaymentService paymentService;

	@Autowired
	protected ApplyService applyService;

	@Autowired
	protected SettleService settleService;

	@Autowired
	protected PushMsgService pushMsgService;

	@Autowired
	protected JPushService jPushService;

	@Autowired
	protected QrCodeService qrCodeService;

	@Autowired
	protected User2PrivateKeyService user2PrivateKeyService;


}
