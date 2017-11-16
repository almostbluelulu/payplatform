package com.yeepay.g3.facade.whitebroad.dto.cashier;

import com.yeepay.g3.facade.whitebroad.annotation.IsSignFile;
import com.yeepay.g3.facade.whitebroad.dto.BasesSignDTO;

/**
 * @ClassName: CashierParam
 * @Description: 收银台参数类
 * @author: dongxulu
 * @date: 17/9/19 下午2:47
 * @version: 1.0.0
 */
public class CashierParam extends BasesSignDTO {

    private static final long serialVersionUID = -7443505686040112719L;
    /**
     *收单商户商编
     */
    @IsSignFile
    private String merchantNo;
    /**
     *订单 token
     */
    @IsSignFile
    private String token;
    /**
     *订单 token 精确到秒
     */
    @IsSignFile
    private String timestamp;
    /**
     *直连参数
     */
    @IsSignFile
    private String directPayType;
    /**
     *卡种
     */
    @IsSignFile
    private String cardType;
    /**
     *用户标识 用户标识,必填,用户在 商户的唯一标识。
     */
    @IsSignFile
    private String userNo;
    /**
     *用户表示类型 IMEI (IMEI )
     MAC (MAC地址 ) USER_ID (用户 ID ) EMAIL (用户 Email ) PHONE (用户手机号 ) ID_CARD (用户身份证号)
     */
    @IsSignFile
    private String userType;
    /**
     *扩展字段
     */
    @IsSignFile
    private String ext;

    /**
     *传入实例 获取签名字符串
     */
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getDirectPayType() {
        return directPayType;
    }

    public void setDirectPayType(String directPayType) {
        this.directPayType = directPayType;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}