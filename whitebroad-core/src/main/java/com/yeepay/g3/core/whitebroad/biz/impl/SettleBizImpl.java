package com.yeepay.g3.core.whitebroad.biz.impl;

import com.yeepay.g3.core.whitebroad.biz.AbstractBiz;
import com.yeepay.g3.core.whitebroad.biz.SettleBiz;
import com.yeepay.g3.core.whitebroad.entity.AccountOpenEntity;
import com.yeepay.g3.core.whitebroad.entity.UserEntity;
import com.yeepay.g3.core.whitebroad.entity.order.SettleEntity;
import com.yeepay.g3.core.whitebroad.parser.impl.SettleDTOConvert;
import com.yeepay.g3.facade.whitebroad.dto.trade.SettleDTO;
import com.yeepay.g3.facade.whitebroad.dto.trade.SettleListRequest;
import com.yeepay.g3.facade.whitebroad.dto.trade.SettleListResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Description: 结算类业务
 * Author: jiawen.huang
 * Date: 2017/9/20
 * Time: 18:57
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class SettleBizImpl extends AbstractBiz implements SettleBiz {

	@Override
	public SettleListResponse findCuzSettleByDate(SettleListRequest request) {
		request.checkParams();
		UserEntity userEntity = userService.findRegisterByUserNo(request.getUserNumber());
		AccountOpenEntity accountOpenEntity = applyService.getAccountOpenEntityByUserNumber(userEntity.getUserNumber());
		int totalCount = settleService.findTotalCount(request.getStartDate(), request.getEndDate(),
				request.getSettleType(), accountOpenEntity.getCustomerNo());
		BigDecimal totalSum = settleService.getTotalSum(request.getStartDate(), request.getEndDate(),
				request.getSettleType(), accountOpenEntity.getCustomerNo());
		int totalPage = (totalCount - 1) / (request.getPageSize() + 1);
		int fromIndex = (request.getPageIndex() - 1) * request.getPageSize(); // 每页第一行
		List<SettleEntity> settleList = settleService.findCuzSettleByDate(accountOpenEntity.getCustomerNo(), null,
				request.getStartDate(), request.getEndDate(), fromIndex, request.getPageSize());
		List<SettleDTO> settleDTOList = new SettleDTOConvert().convert2DTO(settleList);
		SettleListResponse response = new SettleListResponse();
		response.setSettleCount(totalCount);
		response.setTotalPage(totalPage);
		response.setSettleSum(totalSum.toPlainString());
		response.setSettleList(settleDTOList);
		return response;
	}
}
