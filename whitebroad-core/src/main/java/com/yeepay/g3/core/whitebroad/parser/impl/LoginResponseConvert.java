package com.yeepay.g3.core.whitebroad.parser.impl;

import com.yeepay.g3.core.whitebroad.entity.UserEntity;
import com.yeepay.g3.core.whitebroad.entity.WbUserTokenEntity;
import com.yeepay.g3.core.whitebroad.parser.DTOConvert2;
import com.yeepay.g3.core.whitebroad.util.DateUtils;
import com.yeepay.g3.facade.whitebroad.dto.user.LoginResponse;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * Description: LoginResponseConvert
 * Author: jiawen.huang
 * Date: 2017/9/19
 * Time: 16:48
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class LoginResponseConvert implements DTOConvert2<LoginResponse, UserEntity, WbUserTokenEntity> {

	@Override
	public List convert2Entity(LoginResponse loginResponse) {
		return null;
	}

	@Override
	public LoginResponse convert2DTO(UserEntity userEntity, WbUserTokenEntity wbUserTokenEntity) {
		LoginResponse response = new LoginResponse();
		BeanUtils.copyProperties(wbUserTokenEntity, response);
		BeanUtils.copyProperties(userEntity, response);
		response.setRefreshTime(DateUtils.LONG_DATE_FORMAT.format(wbUserTokenEntity.getRefreshTime()));
		response.setExpireTime(DateUtils.LONG_DATE_FORMAT.format(wbUserTokenEntity.getExpireTime()));
		return response;
	}
}
