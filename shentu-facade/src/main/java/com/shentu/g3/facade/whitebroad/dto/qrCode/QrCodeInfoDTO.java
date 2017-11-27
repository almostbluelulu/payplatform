/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.facade.whitebroad.dto.qrCode;

import java.io.Serializable;
/**
 * @ClassName: QrCodeInfoDTO
 * @Description: QrCodeInfoDTO
 * @author: dongxulu
 * @date: 17/9/23 下午2:50
 * @version: 1.0.0
 */
public class QrCodeInfoDTO implements Serializable {


    private static final long serialVersionUID = 524967183401908983L;
    /**
     *二维码url
     */
    private String qrUrl;
    /**
     * 二维码ID
     */
    private String qrId;
    /**
     *网点名称
     */
    private String shopName;

    public String getQrUrl() {
        return qrUrl;
    }

    public void setQrUrl(String qrUrl) {
        this.qrUrl = qrUrl;
    }

    public String getQrId() {
        return qrId;
    }

    public void setQrId(String qrId) {
        this.qrId = qrId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}