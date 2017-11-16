package com.yeepay.g3.facade.whitebroad.facade;

import com.yeepay.g3.facade.whitebroad.dto.trade.OrderRequestDTO;
import com.yeepay.g3.facade.whitebroad.dto.trade.OrderResponseDTO;
import com.yeepay.g3.facade.whitebroad.dto.trade.SettleListRequest;
import com.yeepay.g3.facade.whitebroad.dto.trade.SettleListResponse;

/**
 * 
 * @ClassName: TradeFacade 
 * @Description: 交易、订单接口
 * @author yunpeng.pan
 * @date 2017年9月18日 下午3:32:22 
 *
 */
public interface TradeFacade {

	/**
	 * 查询收款订单列表
	 *
	 * @param requestDTO
	 * @return
	 */
	OrderResponseDTO queryReceiptOrderList(OrderRequestDTO requestDTO);

	/**
	 * 查询结算
	 *
	 * @param requestDTO
	 * @return
	 */
	SettleListResponse querySettleList(SettleListRequest requestDTO);
}
