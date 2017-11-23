/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.app.pay.dto;


/**
 * @ClassName: OprResponseDTO
 * @Description: OprResponseDTO
 * @author: dongxulu
 * @date: 17/9/19 下午1:43
 * @version: 1.0.0
 */
public class TrxResponseDTO extends BaseResponse {

    private static final long serialVersionUID = 8178536574362813358L;
    /**
     *支付链接
     */
    private String payUrl;
    /**
     *系统订单号
     */
    private String orderId;
    /**
     *商户订单号 回传下单信息
     */
    private String merchantOrderId;

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(String merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }
}