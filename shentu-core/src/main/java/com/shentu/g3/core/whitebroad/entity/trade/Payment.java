/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.core.whitebroad.entity.trade;

import com.shentu.g3.facade.whitebroad.enumtype.trx.CardType;
import com.shentu.g3.facade.whitebroad.enumtype.trx.OrderStatus;
import com.shentu.g3.facade.whitebroad.enumtype.trx.PayType;
import com.shentu.g3.facade.whitebroad.enumtype.trx.TrxType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: Payment
 * @Description: Payment
 * @author: dongxulu
 * @date: 17/9/19 上午10:12
 * @version: 1.0.0
 */
public class Payment implements Serializable {

    private static final long serialVersionUID = 439661551335635352L;

    private String id;
    /**
     *版本
     */
    private Integer version;
    /**
     *易宝订单号
     */
    private String yeepayOrderId;
    /**
     *订单号
     */
    private String order_id;
    /**
     *商编
     */
    private String customerNumber;
    /**
     *支付状态
     */
    private OrderStatus payStatus;
    /**
     *支付方式
     */
    private PayType payType;
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
     *银行名称
     */
    private String bankName;
    /**
     *卡号
     */
    private String cardNumber;
    /**
     *卡类型
     */
    private CardType cardType;
    /**
     *交易类型
     */
    private TrxType trxType;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public TrxType getTrxType() {
        return trxType;
    }

    public void setTrxType(TrxType trxType) {
        this.trxType = trxType;
    }

    public String getYeepayOrderId() {
        return yeepayOrderId;
    }

    public void setYeepayOrderId(String yeepayOrderId) {
        this.yeepayOrderId = yeepayOrderId;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public OrderStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(OrderStatus payStatus) {
        this.payStatus = payStatus;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
}