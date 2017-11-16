/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core.whitebroad.service.qrcode;

import com.yeepay.g3.core.whitebroad.entity.qrcode.QrCode;

import java.util.List;

/**
 * @ClassName: QrCodeService
 * @Description: QrCodeService
 * @author: dongxulu
 * @date: 17/9/18 下午7:05
 * @version: 1.0.0
 */
public interface QrCodeService {

    public void insert(QrCode pojo);
    public void insertList(List<QrCode> pojos);
    public void update(QrCode pojo);
    List<QrCode> getActiveQrByCustomer(String customerNumber);
    QrCode getQrCodeByID(String qrID);

}