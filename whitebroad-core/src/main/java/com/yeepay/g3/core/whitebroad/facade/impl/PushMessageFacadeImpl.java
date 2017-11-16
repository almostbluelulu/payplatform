package com.yeepay.g3.core.whitebroad.facade.impl;

import com.yeepay.g3.facade.whitebroad.dto.PushListRequest;
import com.yeepay.g3.facade.whitebroad.dto.PushListResponse;
import com.yeepay.g3.facade.whitebroad.facade.PushMessageFacade;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 2017/10/18
 * Time: 19:45
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Service("pushMessageFacade")
public class PushMessageFacadeImpl extends AbstractFacade implements PushMessageFacade {

	@Override
	public PushListResponse queryMsg(PushListRequest request) {
		return pushMsgBiz.queryMsg(request);
	}
}
