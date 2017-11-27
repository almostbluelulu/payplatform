/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.core.whitebroad.biz;

import com.shentu.g3.facade.whitebroad.dto.opr.*;
import com.shentu.g3.facade.whitebroad.dto.qrCode.QrCodeRequestDTO;
import com.shentu.g3.facade.whitebroad.dto.trade.OrderRequestDTO;
import com.shentu.g3.facade.whitebroad.dto.trade.OrderResponseDTO;

/**
 * @ClassName: OrderServiceBiz
 * @Description: OrderServiceBiz 订单业务服务
 * @author: dongxulu
 * @date: 17/9/19 上午11:25
 * @version: 1.0.0
 */
public interface TradeServiceBiz {


     GetCashierUrlResponseDTO getCashierUrl(GetCashierUrlRequestDTO requestDTO) throws Throwable;

     TrxResponseDTO createOrder(TrxRequestDTO requestDTO) throws Throwable;
     TrxResponseDTO deskQrPay(QrCodeRequestDTO requestDTO) throws Throwable;

     /**
      * 支付回调
      */
     OprCallBackResponseParam payCallBackManager(OprPayCallBackRequestParam param);

     /**
      *清算回调
      */
     OprCallBackResponseParam csCallBackManager(OprCSCallBackRequestParam param);

     /**
      * 
      * @Description: 查询收款订单列表
      * @param @param requestDTO
      * @param @return 
      * @return OrderResponseDTO 返回类型 
      * @author yunpeng.pan
      * @date 2017年9月26日 下午4:38:06
      */
     OrderResponseDTO queryReceiptOrderList(OrderRequestDTO requestDTO);
}