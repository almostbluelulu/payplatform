/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.facade.whitebroad.dto.opr;

import java.io.Serializable;

/**
 * @ClassName: OprCallBackParam
 * @Description: 订单处理器回调参数 对应回调参数字段,如果回调api修改,需要修改此参数类
 * @author: dongxulu
 * @date: 17/9/21 上午10:09
 * @version: 1.0.0
 */
public class OprPayCallBackRequestParam implements Serializable{

    private static final long serialVersionUID = -8174119944539110761L;

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
     *公众号用户 openid
     */
    private String openID;
    /**
     *用户在微信 下的唯一标识
     */
    private String unionID;


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

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getUnionID() {
        return unionID;
    }

    public void setUnionID(String unionID) {
        this.unionID = unionID;
    }
}