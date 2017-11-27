package com.shentu.g3.core.whitebroad.parser.impl;

import com.shentu.g3.core.whitebroad.entity.AppVersionEntity;
import com.shentu.g3.core.whitebroad.parser.DTOConvert;
import com.shentu.g3.facade.whitebroad.dto.user.AppVersionResponse;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 2017/10/17
 * Time: 15:18
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class AppVersionConvert implements DTOConvert<AppVersionResponse, AppVersionEntity> {

	@Override
	public AppVersionEntity convert2Entity(AppVersionResponse appVersionResponse) {
		return null;
	}

	@Override
	public AppVersionResponse convert2DTO(AppVersionEntity versionEntity) {
		AppVersionResponse response = new AppVersionResponse();
		BeanUtils.copyProperties(versionEntity, response);
		response.setRoleType(versionEntity.getRoleType().getValue());
		response.setPlatform(versionEntity.getPlatform().getValue());
		response.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(versionEntity.getUpdateTime()));
		response.setVersionCode(versionEntity.getVersionCode());
		return response;
	}
}
