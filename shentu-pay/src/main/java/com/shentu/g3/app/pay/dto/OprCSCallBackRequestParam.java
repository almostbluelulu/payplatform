/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.app.pay.dto;

import java.io.Serializable;

/**
 * @ClassName: OprCSCallBackRequestParam
 * @Description: OprCSCallBackRequestParam 订单处理器清算回调参数,对应api参数,如有修改,需要修改此类
 * @author: dongxulu
 * @date: 17/10/10 下午4:36
 * @version: 1.0.0
 */
public class OprCSCallBackRequestParam implements Serializable{


    private static final long serialVersionUID = 2234068399561559422L;
    private String parentMerchantNo;

    private String merchantNo;
    /**
     *商户订单号
     */
    private String orderId;

    private String uniqueOrderNo;
    /**
     *订单状态:订单成功,订单关闭,订单超时
     * SUCCESS、CLOSED、 TIMEOUT
     */
    private String status;

    private String orderAmount;
    /**
     *实付金额
     */
    private String payAmount;
    /**
     * 格式 yyyy-MM-dd HH:mm:ss
     */
    private String requestDate;
    /**
     *格式 yyyy-MM-dd HH:mm:ss
     */
    private String paySuccessDate;
    /**
     *格式 yyyy-MM-dd HH:mm:ss
     */
    private String csSuccessDate;
    /**
     *订单状态SUCCESS
     */
    private String orderStatus;
    /**
     *商户手续费
     */
    private String merchantFee;
    /**
     *用户手续费
     */
    private String customerFee;

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

    public String getUniqueOrderNo() {
        return uniqueOrderNo;
    }

    public void setUniqueOrderNo(String uniqueOrderNo) {
        this.uniqueOrderNo = uniqueOrderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getPaySuccessDate() {
        return paySuccessDate;
    }

    public void setPaySuccessDate(String paySuccessDate) {
        this.paySuccessDate = paySuccessDate;
    }

    public String getCsSuccessDate() {
        return csSuccessDate;
    }

    public void setCsSuccessDate(String csSuccessDate) {
        this.csSuccessDate = csSuccessDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getMerchantFee() {
        return merchantFee;
    }

    public void setMerchantFee(String merchantFee) {
        this.merchantFee = merchantFee;
    }

    public String getCustomerFee() {
        return customerFee;
    }

    public void setCustomerFee(String customerFee) {
        this.customerFee = customerFee;
    }
}