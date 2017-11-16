/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core.whitebroad.biz;

import com.yeepay.g3.core.whitebroad.entity.trade.Order;
import com.yeepay.g3.facade.whitebroad.dto.opr.OprOrderParamRequestDTO;
import com.yeepay.g3.facade.whitebroad.dto.opr.OprOrderParamResponseDTO;

import java.util.Map;
/**
 * @ClassName: OprServiceBiz
 * @Description: OprServiceBiz
 * @author: dongxulu
 * @date: 17/9/21 上午11:24
 * @version: 1.0.0
 */
public interface OprServiceBiz {

    /**
     * 调用订单处理器下单
     */
    public OprOrderParamResponseDTO creatOprOrder(OprOrderParamRequestDTO requestDTO) throws Throwable;

    /**
     * 将DTO转化成map
     */
    public Map<String, Object> getOprParam(OprOrderParamRequestDTO requestDTO) throws Throwable;

    /**
     * 获取收银台支付链接
     */
    public String getPayUrl(Order order);

}