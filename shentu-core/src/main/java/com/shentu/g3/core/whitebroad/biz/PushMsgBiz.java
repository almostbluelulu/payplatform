package com.shentu.g3.core.whitebroad.biz;

import com.shentu.g3.facade.whitebroad.dto.PushListRequest;
import com.shentu.g3.facade.whitebroad.dto.PushListResponse;

/**
 * Description:推送
 * Author: jiawen.huang
 * Date: 2017/10/18
 * Time: 19:47
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface PushMsgBiz {

	/**
	 * 查询消息
	 *
	 * @param request
	 * @return
	 */
	PushListResponse queryMsg(PushListRequest request);
}
