/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.facade.whitebroad.facade;

import com.shentu.g3.facade.whitebroad.dto.opr.*;
import com.shentu.g3.facade.whitebroad.dto.qrCode.QrCodeRequestDTO;

/**
 * @ClassName: TrxManageFacade
 * @Description: TrxManageFacade 交易处理服务
 * @author: dongxulu
 * @date: 17/9/19 上午11:39
 * @version: 1.0.0
 */
public interface TrxManageFacade {

    /**
     * 去大算下单
     */
    public TrxResponseDTO scanPay(TrxRequestDTO requestDTO);
    /**
     * 去大算下单
     */
    public TrxResponseDTO deskQrPay(QrCodeRequestDTO requestDTO);

    /**
     *根据系统订单获取收银台支付链接
     */
    public GetCashierUrlResponseDTO getCashierUrl(GetCashierUrlRequestDTO requestDTO);

    /**
     * 订单处理器支付回调
     */
    public OprCallBackResponseParam  payCallBack(OprPayCallBackRequestParam oprPayCallBackParam);

    /**
     * 订单处理器清算回调
     */
    public OprCallBackResponseParam  csCallBack(OprCSCallBackRequestParam oprPayCallBackParam);

}