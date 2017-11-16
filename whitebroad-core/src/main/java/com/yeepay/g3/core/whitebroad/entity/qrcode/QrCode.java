/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core.whitebroad.entity.qrcode;

import com.yeepay.g3.core.whitebroad.entity.IdEntity;
import com.yeepay.g3.facade.whitebroad.enumtype.qrcode.Status;

/**
 * @ClassName: QrCode
 * @Description: QrCode 二维码实体类
 * @author: dongxulu
 * @date: 17/9/18 下午6:24
 * @version: 1.0.0
 */
public class QrCode extends IdEntity {

    private static final long serialVersionUID = 7048240838222256483L;
    /**
     * 二维码id
     */
    private String qrId;
    /**
     * 二维码链接
     */
    private String qrUrl;
    /**
     *商户编号
     */
    private String customerNumber;
    /**
     *网点编号
     */
    private String shopNumber;
    /**
     *用户编号
     */
    private String userNumber;
    /**
     *备注
     */
    private String remark;
    /**
     *二维码状态
     */
    private Status status = Status.INIT;
    /**
     * 关联id 批量采购订单号或者单据采购号
     */
    private String relationId;

    public String getQrId() {
        return qrId;
    }

    public void setQrId(String qrId) {
        this.qrId = qrId;
    }

    public String getQrUrl() {
        return qrUrl;
    }

    public void setQrUrl(String qrUrl) {
        this.qrUrl = qrUrl;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }
}