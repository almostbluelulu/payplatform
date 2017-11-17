package com.shentu.g3.core.whitebroad.facade;

import com.google.gson.Gson;
import com.shentu.g3.core.whitebroad.BaseTest;
import com.shentu.g3.facade.whitebroad.dto.PushListRequest;
import com.shentu.g3.facade.whitebroad.dto.PushListResponse;
import com.shentu.g3.facade.whitebroad.dto.trade.OrderRequestDTO;
import com.shentu.g3.facade.whitebroad.dto.trade.OrderResponseDTO;
import com.shentu.g3.facade.whitebroad.dto.trade.SettleListRequest;
import com.shentu.g3.facade.whitebroad.dto.trade.SettleListResponse;
import com.shentu.g3.facade.whitebroad.facade.PushMessageFacade;
import com.shentu.g3.facade.whitebroad.facade.TradeFacade;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description: 查询类
 * Author: jiawen.huang
 * Date: 2017/10/16
 * Time: 14:08
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class QueryTest extends BaseTest {

	@Autowired
	private TradeFacade tradeFacade;

	@Autowired
	private PushMessageFacade pushMessageFacade;

	@Test
	public void querySettleList() {
		SettleListRequest dto = new SettleListRequest();
		dto.setStartDate("2017-10-13");
		dto.setEndDate("2017-10-13");
		dto.setUserNumber("311234567807");
		SettleListResponse responseDto = tradeFacade.querySettleList(dto);
		System.out.println(responseDto);
	}


	@Test
	public void queryReceiptOrderList() {
		OrderRequestDTO dto = new OrderRequestDTO();
		dto.setStartDate("2017-09-13");
		dto.setEndDate("2017-10-13");
		dto.setUserNumber("311234567802");
		dto.setOrderId("wbtrx11234567809");
		OrderResponseDTO responseDto = tradeFacade.queryReceiptOrderList(dto);
		System.out.println(new Gson().toJson(responseDto));
	}

	@Test
	public void queryPush() {
		PushListRequest dto = new PushListRequest();
		dto.setStartDate("2017-10-16 00:00:00");
		dto.setEndDate("2017-10-17 00:00:00");
		dto.setUserNumber("311234567802");
		PushListResponse responseDto = pushMessageFacade.queryMsg(dto);
		System.out.println(new Gson().toJson(responseDto));
	}


}
