package com.yeepay.g3.facade.whitebroad.facade;

import com.yeepay.g3.facade.whitebroad.dto.PushListRequest;
import com.yeepay.g3.facade.whitebroad.dto.PushListResponse;

/**
 * Description: 推送查询接口
 * Author: jiawen.huang
 * Date: 2017/10/18
 * Time: 18:56
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface PushMessageFacade {

	/**
	 * 查询消息
	 *
	 * @param request
	 * @return
	 */
	PushListResponse queryMsg(PushListRequest request);
}
