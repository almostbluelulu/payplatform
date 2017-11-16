/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.facade.whitebroad.dto.opr;

import com.yeepay.g3.facade.whitebroad.dto.BaseResponse;
/**
 * @ClassName: getCashierUrlResponseDTO
 * @Description: getCashierUrlResponseDTO
 * @author: dongxulu
 * @date: 17/9/19 下午3:28
 * @version: 1.0.0
 */
public class GetCashierUrlResponseDTO extends BaseResponse {
    private static final long serialVersionUID = -271143185412917953L;
    /**
     * 跳转收银台地址
     */
    private String payUrl;

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }
}