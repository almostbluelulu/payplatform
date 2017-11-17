package com.shentu.g3.core.whitebroad.biz.impl;

import com.shentu.g3.core.whitebroad.biz.AbstractBiz;
import com.shentu.g3.core.whitebroad.biz.AppVersionBiz;
import com.shentu.g3.core.whitebroad.entity.AppVersionEntity;
import com.shentu.g3.core.whitebroad.parser.impl.AppVersionConvert;
import com.shentu.g3.facade.whitebroad.dto.user.AppVersionRequset;
import com.shentu.g3.facade.whitebroad.dto.user.AppVersionResponse;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;
import org.springframework.stereotype.Component;

/**
 * Description:版本管理和控制biz
 * Author: jiawen.huang
 * Date: 2017/10/17
 * Time: 15:05
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class AppVersionBizImpl extends AbstractBiz implements AppVersionBiz {

	@Override
	public AppVersionResponse checkNew(AppVersionRequset request) {
		AppVersionEntity versionEntity = appVersionService.findById(request.getVersionId());
		AppVersionEntity newVersionEntity = appVersionService.findNewByRoleAndPlatform(versionEntity.getPlatform(), versionEntity.getRoleType());
		if (null != newVersionEntity && versionEntity.getVersionCode().compareTo(
				newVersionEntity.getVersionCode()) < 0) {
			return new AppVersionConvert().convert2DTO(newVersionEntity);
		} else {
			throw new WbSysException(ErrorCode.APP_DONT_NEED_UPDATE);
		}
	}
}
