/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.facade.whitebroad.dto.opr;


import java.io.Serializable;
/**
 * @ClassName: OprOrderParamResponseDTO
 * @Description: 订单处理器下单同步返回参数
 * @author: dongxulu
 * @date: 17/9/19 下午9:25
 * @version: 1.0.0
 */
public class OprOrderParamResponseDTO implements Serializable {

    private static final long serialVersionUID = -5526871644122377298L;
    /**
     *返回码 OPR00000 表示成功
     */
    private String code;
    /**
     * 信息描述,中文,对应 code 的中文信息
     */
    private String message;
    /**
     *入参回传
     */
    private String parentMerchantNo;
    /**
     *
     */
    private String merchantNo;
    /**
     *
     */
    private String orderId;
    /**
     * 易宝内部生成唯一订单流水号
     */
    private String uniqueOrderNo;
    /**
     *订单 token
     */
    private String token;
    /**
     *
     */
    private String goodsParamExt;
    /**
     *
     */
    private String memo;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getGoodsParamExt() {
        return goodsParamExt;
    }

    public void setGoodsParamExt(String goodsParamExt) {
        this.goodsParamExt = goodsParamExt;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}