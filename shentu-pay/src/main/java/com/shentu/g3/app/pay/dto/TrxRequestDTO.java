/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.app.pay.dto;


import com.shentu.g3.app.pay.enums.PayType;
import com.shentu.g3.app.pay.enums.ScanType;

/**
 * @ClassName: OprRequestDTO
 * @Description: OprRequestDTO 订单处理器下单DTO
 * @author: dongxulu
 * @date: 17/9/19 下午1:37
 * @version: 1.0.0
 */
public class TrxRequestDTO extends BaseRequest {


    private static final long serialVersionUID = 8982036550480923692L;
    /**
     *父商编没有与收单商编保持一致
     */
    private String parentMerchantNo;
    /**
     * 收单商编
     */
    private String merchantNo;
    /**
     *订单号
     */
    private String merchantOrderId;
    /**
     *订单金额
     */
    private String orderAmount;
    /**
     *商品名称
     */
    private String goodsName;
    /**
     *商品描述
     */
    private String goodsDesc;
    /**
     *支付类型
     */
    private PayType payType;
    /**
     * 扫码类型
     */
    private ScanType scanType;

    /**
     * 二维码ID
     */
    private String qrId;

    public String getQrId() {
        return qrId;
    }

    public void setQrId(String qrId) {
        this.qrId = qrId;
    }

    public ScanType getScanType() {
        return scanType;
    }

    public void setScanType(ScanType scanType) {
        this.scanType = scanType;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

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

    public String getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(String merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }
}