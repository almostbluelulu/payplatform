package com.shentu.g3.facade.whitebroad.facade;

import com.shentu.g3.facade.whitebroad.dto.user.AppVersionRequset;
import com.shentu.g3.facade.whitebroad.dto.user.AppVersionResponse;

/**
 * Description: 版本管理facade
 * Author: jiawen.huang
 * Date: 16/11/28
 * Time: 10:31
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface AppVersionFacade {

	/**
	 * 查询是否有新版
	 *
	 * @param request
	 * @return
	 */
	AppVersionResponse checkNew(AppVersionRequset request);
}
