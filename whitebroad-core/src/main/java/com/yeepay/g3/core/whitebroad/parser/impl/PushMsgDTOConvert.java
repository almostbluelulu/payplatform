package com.yeepay.g3.core.whitebroad.parser.impl;

import com.yeepay.g3.core.whitebroad.entity.PushMsgEntity;
import com.yeepay.g3.core.whitebroad.parser.DTOConvert;
import com.yeepay.g3.core.whitebroad.util.DateUtils;
import com.yeepay.g3.facade.whitebroad.dto.PushMsgDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 消息
 * Author: jiawen.huang
 * Date: 2017/10/12
 * Time: 16:05
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class PushMsgDTOConvert implements DTOConvert<PushMsgDTO, PushMsgEntity> {

	@Override
	public PushMsgEntity convert2Entity(PushMsgDTO pushMsgDTO) {
		return null;
	}

	@Override
	public PushMsgDTO convert2DTO(PushMsgEntity entity) {
		PushMsgDTO settleDTO = new PushMsgDTO();
		BeanUtils.copyProperties(entity, settleDTO);
		settleDTO.setLifeStart(DateUtils.toString(entity.getLifeStart(), DateUtils.DATE_FORMAT_DATETIME));
		settleDTO.setLifeEnd(DateUtils.toString(entity.getLifeEnd(), DateUtils.DATE_FORMAT_DATETIME));
		settleDTO.setPushTime(DateUtils.toString(entity.getPushTime(), DateUtils.DATE_FORMAT_DATETIME));
		settleDTO.setType(entity.getType().getValue());
		return settleDTO;
	}

	public List<PushMsgDTO> convert2DTO(List<PushMsgEntity> entityList) {
		List<PushMsgDTO> pushMsgDTOList = new ArrayList<PushMsgDTO>();
		for (PushMsgEntity entity : entityList) {
			pushMsgDTOList.add(convert2DTO(entity));
		}
		return pushMsgDTOList;
	}
}
