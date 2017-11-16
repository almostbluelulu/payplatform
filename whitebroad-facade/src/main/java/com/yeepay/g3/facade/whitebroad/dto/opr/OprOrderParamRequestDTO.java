/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.facade.whitebroad.dto.opr;

import com.yeepay.g3.facade.whitebroad.annotation.IsSignFile;
import com.yeepay.g3.facade.whitebroad.dto.BasesSignDTO;

/**
 * @ClassName: OprOrderParamDTO
 * @Description: 订单处理器下单参数
 * @author: dongxulu
 * @date: 17/9/19 下午9:16
 * @version: 1.0.0
 */

public class OprOrderParamRequestDTO extends BasesSignDTO {


    private static final long serialVersionUID = 7170748362157237416L;
    /**
     *父商编没有与收单商编保持一致
     */
    @IsSignFile
    private String parentMerchantNo;
    /**
     * 收单商编
     */
    @IsSignFile
    private String merchantNo;
    /**
     *订单号
     */
    @IsSignFile
    private String orderId;
    /**
     *订单金额
     */
    @IsSignFile
    private String orderAmount;
    /**
     * 有效期 单位:分钟,默认 24 小时,最小 1 分钟,1 最大 180 天
     */
    private String timeoutExpress;
    /**
     * 格式 yyyy-MM-dd HH:mm:ss
     */
    private String requestDate;
    /**
     *支付成功页面回调地址
     */
    private String redirectUrl;
    /**
     *支付成功服务器回调地址
     */
    @IsSignFile
    private String notifyUrl;
    /**
     *商品扩展参数 Json 格式,key 支持 goodsName(必 填)、goodsDesc
     */
    private String goodsParamExt;
    /**
     *支付扩展参数
     */
    private String paymentPaamExt;
    /**
     *行业扩展参数
     */
    private String industryParamExt;
    /**
     *自定义对账备注
     */
    private String memo;
    /**
     *风控信息
     */
    private String riskParamExt;
    /**
     *清算成功回调地 址
     */
    private String csUrl;
    /**
     *资金处理类型,可选值: DELAY_SETTLE("延迟结算"), REAL_TIME("实时订单");
     */
    private String fundProcessType;

    public String getParentMerchantNo() {
        return parentMerchantNo;
    }

    public void setParentMerchantNo(String parentMerchantNo) {
        this.parentMerchantNo = parentMerchantNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getTimeoutExpress() {
        return timeoutExpress;
    }

    public void setTimeoutExpress(String timeoutExpress) {
        this.timeoutExpress = timeoutExpress;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getGoodsParamExt() {
        return goodsParamExt;
    }

    public void setGoodsParamExt(String goodsParamExt) {
        this.goodsParamExt = goodsParamExt;
    }

    public String getPaymentPaamExt() {
        return paymentPaamExt;
    }

    public void setPaymentPaamExt(String paymentPaamExt) {
        this.paymentPaamExt = paymentPaamExt;
    }

    public String getIndustryParamExt() {
        return industryParamExt;
    }

    public void setIndustryParamExt(String industryParamExt) {
        this.industryParamExt = industryParamExt;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getRiskParamExt() {
        return riskParamExt;
    }

    public void setRiskParamExt(String riskParamExt) {
        this.riskParamExt = riskParamExt;
    }

    public String getCsUrl() {
        return csUrl;
    }

    public void setCsUrl(String csUrl) {
        this.csUrl = csUrl;
    }

    public String getFundProcessType() {
        return fundProcessType;
    }

    public void setFundProcessType(String fundProcessType) {
        this.fundProcessType = fundProcessType;
    }
}