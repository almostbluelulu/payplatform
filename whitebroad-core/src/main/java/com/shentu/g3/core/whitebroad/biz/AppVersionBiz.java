package com.shentu.g3.core.whitebroad.biz;

import com.shentu.g3.facade.whitebroad.dto.user.AppVersionRequset;
import com.shentu.g3.facade.whitebroad.dto.user.AppVersionResponse;

/**
 * Description: 版本管理和控制
 * Author: jiawen.huang
 * Date: 16/11/27
 * Time: 21:29
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface AppVersionBiz {

	/**
	 * 查询是否有新版
	 *
	 * @param request
	 * @return
	 */
	AppVersionResponse checkNew(AppVersionRequset request);
}
