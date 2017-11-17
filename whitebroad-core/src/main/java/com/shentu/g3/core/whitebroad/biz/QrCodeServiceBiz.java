/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.core.whitebroad.biz;

import com.shentu.g3.facade.whitebroad.dto.qrCode.QrCodeRequestDTO;
import com.shentu.g3.facade.whitebroad.dto.qrCode.QrCodeResponseDTO;

/**
 * @ClassName: QrCodeServiceBiz
 * @Description: QrCodeServiceBiz 台牌相关业务服务
 * @author: dongxulu
 * @date: 17/9/19 上午11:20
 * @version: 1.0.0
 */
public interface QrCodeServiceBiz {

        QrCodeResponseDTO getQrCodeInfo(QrCodeRequestDTO requestDTO);

        QrCodeResponseDTO getCustomerByQrid(QrCodeRequestDTO requestDTO);
}