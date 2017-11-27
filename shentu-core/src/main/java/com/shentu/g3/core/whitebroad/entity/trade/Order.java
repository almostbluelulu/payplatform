/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.core.whitebroad.entity.trade;
/**
 * 类名称: Order <br>
 * 类描述: <br>
 *
 * @author: xxxx.xxx
 * @since: 17/9/18 下午3:38
 * @version: 1.0.0
 */


import com.shentu.g3.facade.whitebroad.enumtype.trx.OrderStatus;
import com.shentu.g3.facade.whitebroad.enumtype.trx.OrderType;
import com.shentu.g3.facade.whitebroad.enumtype.trx.PayType;
import com.shentu.g3.facade.whitebroad.enumtype.trx.ScanType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable {

    private static final long serialVersionUID = -4652907958517293444L;
    /**
     *系统流水 唯一
     */
    private String id;

    private Integer version;
    /**
     * 商编
     */
    private String customerNumber;
    /**
     * 商户订单号
     */
    private String requestNo;
    /**
     * 易宝订单号
     */
    private String yeepayOrderId;
    /**
     * 下单用户
     */
    private String userNumber;
    /**
     * 订单token 存储订单处理器下单token
     */
    private String token;

    private PayType payType;
    /**
     * 二维码编号
     */
    private String qrID;
    /**
     * 订单类型
     */
    private OrderType orderType = OrderType.T1;
    /**
     * 下单类型
     */
    private ScanType scanType;
    /**
     * 订单状态
     */
    private OrderStatus orderStatus = OrderStatus.INIT;
    /**
     * 交易金额
     */
    private BigDecimal trxAmt;
    /**
     *实际支付金额
     */
    private BigDecimal realAmount;
    /**
     * 手续费
     */
    private BigDecimal feeAmount;
    /**
     *总退款金额
     */
    private BigDecimal refundAmount;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *完成时间
     */
    private Date completeTime;
    /**
     *更新时间
     */
    private Date updateTime;
    /**
     *错误编码
     */
    private String errCode;
    /**
     *错误信息
     */
    private String errMessage;
    /**
     *备注
     */
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getYeepayOrderId() {
        return yeepayOrderId;
    }

    public void setYeepayOrderId(String yeepayOrderId) {
        this.yeepayOrderId = yeepayOrderId;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getQrID() {
        return qrID;
    }

    public void setQrID(String qrID) {
        this.qrID = qrID;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public ScanType getScanType() {
        return scanType;
    }

    public void setScanType(ScanType scanType) {
        this.scanType = scanType;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTrxAmt() {
        return trxAmt;
    }

    public void setTrxAmt(BigDecimal trxAmt) {
        this.trxAmt = trxAmt;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}