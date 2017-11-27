package com.shentu.g3.core.whitebroad.parser.impl;

import org.springframework.beans.BeanUtils;

import com.shentu.g3.core.whitebroad.entity.WbUserTokenEntity;
import com.shentu.g3.core.whitebroad.parser.DTOConvert;
import com.shentu.g3.facade.whitebroad.dto.auth.UserAuthDTO;

public class UserTokenConvert implements DTOConvert<UserAuthDTO, WbUserTokenEntity> {

	@Override
	public WbUserTokenEntity convert2Entity(UserAuthDTO dto) {
		WbUserTokenEntity entity = new WbUserTokenEntity();
		if(dto != null) {
			BeanUtils.copyProperties(dto, entity);
		}
		return entity;
	}

	@Override
	public UserAuthDTO convert2DTO(WbUserTokenEntity entity) {
		UserAuthDTO userAuthDto = new UserAuthDTO();
		if(entity != null) {
			BeanUtils.copyProperties(entity, userAuthDto);
		}
		return userAuthDto;
	}
	
}