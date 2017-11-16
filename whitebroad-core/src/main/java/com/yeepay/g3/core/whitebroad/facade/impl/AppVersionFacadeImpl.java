package com.yeepay.g3.core.whitebroad.facade.impl;

import com.yeepay.g3.facade.whitebroad.dto.user.AppVersionRequset;
import com.yeepay.g3.facade.whitebroad.dto.user.AppVersionResponse;
import com.yeepay.g3.facade.whitebroad.facade.AppVersionFacade;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 2017/10/17
 * Time: 15:01
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Service("appVersionFacade")
public class AppVersionFacadeImpl extends AbstractFacade implements AppVersionFacade {

	@Override
	public AppVersionResponse checkNew(AppVersionRequset request) {
		return appVersionBiz.checkNew(request);
	}
}