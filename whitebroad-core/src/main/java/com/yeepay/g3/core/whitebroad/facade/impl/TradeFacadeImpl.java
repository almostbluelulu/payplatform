package com.yeepay.g3.core.whitebroad.facade.impl;

import com.yeepay.g3.facade.whitebroad.dto.trade.OrderRequestDTO;
import com.yeepay.g3.facade.whitebroad.dto.trade.OrderResponseDTO;
import com.yeepay.g3.facade.whitebroad.dto.trade.SettleListRequest;
import com.yeepay.g3.facade.whitebroad.dto.trade.SettleListResponse;
import com.yeepay.g3.facade.whitebroad.facade.TradeFacade;
import org.springframework.stereotype.Service;

/**
 * 
 * @ClassName: 
 * @Description: 交易、订单接口
 * @author yunpeng.pan
 * @date 2017年9月26日 下午4:39:44 
 *
 */
@Service("tradeFacade")
public class TradeFacadeImpl extends AbstractFacade implements TradeFacade {

	@Override
	public OrderResponseDTO queryReceiptOrderList(OrderRequestDTO requestDTO) {
		return tradeServiceBiz.queryReceiptOrderList(requestDTO);
	}

	@Override
	public SettleListResponse querySettleList(SettleListRequest requestDTO) {
		return settleBiz.findCuzSettleByDate(requestDTO);
	}


}
