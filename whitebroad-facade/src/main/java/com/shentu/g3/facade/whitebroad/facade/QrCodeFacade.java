/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.facade.whitebroad.facade;

import com.shentu.g3.facade.whitebroad.dto.qrCode.QrCodeRequestDTO;
import com.shentu.g3.facade.whitebroad.dto.qrCode.QrCodeResponseDTO;

/**
 * @ClassName: QrCodeFacade
 * @Description: QrCodeFacade
 * @author: dongxulu
 * @date: 17/9/23 下午2:39
 * @version: 1.0.0
 */
public interface QrCodeFacade {

    /**
     *获取二维码信息
     */
   public QrCodeResponseDTO getQrCodeInfo(QrCodeRequestDTO requestDTO);
    /**
     *根据台签id获取用户信息
     */
    public QrCodeResponseDTO getCustomerByQrid(QrCodeRequestDTO requestDTO);
}