package com.shentu.g3.core.whitebroad.parser.impl;

import org.springframework.beans.BeanUtils;

import com.shentu.g3.core.whitebroad.entity.WbApiConfigEntity;
import com.shentu.g3.core.whitebroad.parser.DTOConvert;
import com.shentu.g3.facade.whitebroad.dto.api.ApiInfoDTO;

public class ApiConfigConvert implements DTOConvert<ApiInfoDTO, WbApiConfigEntity> {

	@Override
	public WbApiConfigEntity convert2Entity(ApiInfoDTO dto) {
		WbApiConfigEntity entity = new WbApiConfigEntity();
		if(dto != null) {
			BeanUtils.copyProperties(dto, entity);
		}
		return entity;
	}

	@Override
	public ApiInfoDTO convert2DTO(WbApiConfigEntity entity) {
		ApiInfoDTO apiInfoDTO = new ApiInfoDTO();
		if(entity != null) {
			BeanUtils.copyProperties(entity, apiInfoDTO);
		}
		return apiInfoDTO;
	}
	
}