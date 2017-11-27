package com.shentu.g3.core.whitebroad.parser.impl;

import com.shentu.g3.core.whitebroad.entity.WbUserTokenEntity;
import com.shentu.g3.core.whitebroad.parser.DTOConvert;
import com.shentu.g3.core.whitebroad.util.DateUtils;
import com.shentu.g3.facade.whitebroad.dto.user.RefreshTKResponse;
import org.springframework.beans.BeanUtils;

/**
 * Description: RefreshTokenDTOConvert
 * Author: jiawen.huang
 * Date: 2017/9/19
 * Time: 17:23
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class RefreshTokenDTOConvert implements DTOConvert<RefreshTKResponse, WbUserTokenEntity> {

	@Override
	public WbUserTokenEntity convert2Entity(RefreshTKResponse refreshTKResponse) {
		return null;
	}

	@Override
	public RefreshTKResponse convert2DTO(WbUserTokenEntity wbUserTokenEntity) {
		RefreshTKResponse response = new RefreshTKResponse();
		BeanUtils.copyProperties(wbUserTokenEntity, response);
		response.setRefreshTime(DateUtils.LONG_DATE_FORMAT.format(wbUserTokenEntity.getRefreshTime()));
		response.setExpireTime(DateUtils.LONG_DATE_FORMAT.format(wbUserTokenEntity.getExpireTime()));
		return response;
	}
}
