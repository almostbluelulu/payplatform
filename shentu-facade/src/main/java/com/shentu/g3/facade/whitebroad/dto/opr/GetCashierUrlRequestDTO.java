/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.facade.whitebroad.dto.opr;

import com.shentu.g3.facade.whitebroad.dto.BaseRequest;

/**
 * @ClassName: getCashierUrlDTO
 * @Description: getCashierUrlDTO
 * @author: dongxulu
 * @date: 17/9/19 下午3:26
 * @version: 1.0.0
 */
public class GetCashierUrlRequestDTO extends BaseRequest {
    private static final long serialVersionUID = 6820642062511163989L;
    /**
     *系统流水号
     */
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}