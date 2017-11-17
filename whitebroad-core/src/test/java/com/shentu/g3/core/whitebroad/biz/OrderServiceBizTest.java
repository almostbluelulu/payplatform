/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.core.whitebroad.biz;

import com.google.gson.Gson;
import com.shentu.g3.core.whitebroad.BaseTest;
import com.shentu.g3.facade.whitebroad.dto.trade.OrderRequestDTO;
import com.shentu.g3.facade.whitebroad.dto.trade.OrderResponseDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class OrderServiceBizTest extends BaseTest {
    @Autowired
    TradeServiceBiz tradeServiceBiz;
    @Test
    public void getUrl(){
    	OrderRequestDTO dto = new OrderRequestDTO();
    	dto.setStartDate("2017-09-23");
    	dto.setEndDate("2017-09-25");
    	dto.setUserNumber("101");
    	dto.setOrderStatus("ALL");
    	dto.setPayType("ALL");
    	
    	OrderResponseDTO responseDto = tradeServiceBiz.queryReceiptOrderList(dto);
		System.out.println(new Gson().toJson(responseDto));
    }


}