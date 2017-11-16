package com.yeepay.g3.core.whitebroad.parser.impl;

import com.yeepay.g3.core.whitebroad.entity.order.SettleEntity;
import com.yeepay.g3.core.whitebroad.parser.DTOConvert;
import com.yeepay.g3.core.whitebroad.util.DateUtils;
import com.yeepay.g3.facade.whitebroad.dto.trade.SettleDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 结算
 * Author: jiawen.huang
 * Date: 2017/10/12
 * Time: 16:05
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class SettleDTOConvert implements DTOConvert<SettleDTO, SettleEntity> {

	@Override
	public SettleEntity convert2Entity(SettleDTO settleDTO) {
		return null;
	}

	@Override
	public SettleDTO convert2DTO(SettleEntity entity) {
		SettleDTO settleDTO = new SettleDTO();
		BeanUtils.copyProperties(entity, settleDTO);
		settleDTO.setSettleAmount(entity.getSettleAmount().toPlainString());
		settleDTO.setSettleDate(DateUtils.toString(entity.getSettleDate(), DateUtils.DATE_FORMAT_DATETIME));
		settleDTO.setSettleFee(entity.getSettleFee().toPlainString());
		settleDTO.setSettleType(entity.getSettleType().getValue());
		return settleDTO;
	}

	public List<SettleDTO> convert2DTO(List<SettleEntity> entityList) {
		List<SettleDTO> settleDTOList = new ArrayList<SettleDTO>();
		for (SettleEntity entity : entityList) {
			settleDTOList.add(convert2DTO(entity));
		}
		return settleDTOList;
	}
}
