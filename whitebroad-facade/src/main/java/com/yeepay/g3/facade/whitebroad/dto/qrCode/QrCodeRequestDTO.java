/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.facade.whitebroad.dto.qrCode;

import com.yeepay.g3.facade.whitebroad.dto.BaseRequest;

/**
 * @ClassName: QrCodeRequestDTO
 * @Description: QrCodeRequestDTO
 * @author: dongxulu
 * @date: 17/9/23 下午2:41
 * @version: 1.0.0
 */
public class QrCodeRequestDTO extends BaseRequest {
    private static final long serialVersionUID = 5760105955254803801L;
    /**
     *商编号
     */
    private String customerNumber;
    /**
     *网点编号
     */
    private String shopNumber;
    /**
     *二维码编号
     */
    private String qrId;

    public String getQrId() {
        return qrId;
    }

    public void setQrId(String qrId) {
        this.qrId = qrId;
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
}