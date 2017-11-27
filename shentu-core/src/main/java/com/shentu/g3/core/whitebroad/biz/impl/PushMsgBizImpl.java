package com.shentu.g3.core.whitebroad.biz.impl;

import com.shentu.g3.core.whitebroad.biz.AbstractBiz;
import com.shentu.g3.core.whitebroad.entity.PushMsgEntity;
import com.shentu.g3.core.whitebroad.biz.PushMsgBiz;
import com.shentu.g3.core.whitebroad.entity.UserEntity;
import com.shentu.g3.core.whitebroad.parser.impl.PushMsgDTOConvert;
import com.shentu.g3.facade.whitebroad.dto.PushListRequest;
import com.shentu.g3.facade.whitebroad.dto.PushListResponse;
import com.shentu.g3.facade.whitebroad.dto.PushMsgDTO;
import com.shentu.g3.facade.whitebroad.enumtype.MsgTypeEnum;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:PushMsgBiz impl
 * Author: jiawen.huang
 * Date: 2017/10/18
 * Time: 19:48
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class PushMsgBizImpl extends AbstractBiz implements PushMsgBiz {

	@Override
	public PushListResponse queryMsg(PushListRequest request) {
		request.checkParams();
		UserEntity userEntity = userService.findRegisterByUserNo(request.getUserNumber());
		int totalCount = pushMsgService.findTotalCount(request.getStartDate(), request.getEndDate(),
				MsgTypeEnum.parse(request.getType()), userEntity.getUserNumber());
		int totalPage = (totalCount - 1) / (request.getPageSize() + 1);
		int fromIndex = (request.getPageIndex() - 1) * request.getPageSize();
		List<PushMsgEntity> pushMsgList = pushMsgService.findMsgByDate(userEntity.getUserNumber(), MsgTypeEnum.parse(request.getType()),
				request.getStartDate(), request.getEndDate(), fromIndex, request.getPageSize());
		List<PushMsgDTO> pushMsgDTOList = new PushMsgDTOConvert().convert2DTO(pushMsgList);
		PushListResponse response = new PushListResponse();
		response.setTotalPage(totalPage);
		response.setMsgCount(totalCount);
		response.setMsgList(pushMsgDTOList);
		return response;
	}
}
