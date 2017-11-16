/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core.whitebroad.facade.impl;

import com.yeepay.g3.core.whitebroad.biz.QrCodeServiceBiz;
import com.yeepay.g3.facade.whitebroad.dto.ResponseStatus;
import com.yeepay.g3.facade.whitebroad.dto.qrCode.QrCodeRequestDTO;
import com.yeepay.g3.facade.whitebroad.dto.qrCode.QrCodeResponseDTO;
import com.yeepay.g3.facade.whitebroad.exception.ErrorCode;
import com.yeepay.g3.facade.whitebroad.exception.WbSysException;
import com.yeepay.g3.facade.whitebroad.facade.QrCodeFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: QrCodeFacadeImpl
 * @Description: QrCodeFacadeImpl
 * @author: dongxulu
 * @date: 17/9/23 下午4:59
 * @version: 1.0.0
 */
@Service("qrCodeFacade")
public class QrCodeFacadeImpl implements QrCodeFacade {
    private final static Log log = LogFactory.getLog(QrCodeFacadeImpl.class);
    @Autowired
    QrCodeServiceBiz qrCodeServiceBiz;
    @Override
    public QrCodeResponseDTO getQrCodeInfo(QrCodeRequestDTO requestDTO) {
        QrCodeResponseDTO  responseDTO = new QrCodeResponseDTO();
        try {
            responseDTO = qrCodeServiceBiz.getQrCodeInfo(requestDTO);
        } catch (Throwable throwable) {
            log.info("getQrCodeInfo exception:",throwable);
            if(throwable instanceof WbSysException){
                WbSysException wbSysException = (WbSysException) throwable;
                responseDTO.setErrCode(wbSysException.getDefineCode());
                responseDTO.setErrMsg(wbSysException.getMessage());
            }
            responseDTO.setErrCode(ErrorCode.SYSTEM_EXCEPTION);
            responseDTO.setStatus(ResponseStatus.FAILURE);
            return responseDTO;
        }
        return responseDTO;
    }

    @Override
    public QrCodeResponseDTO getCustomerByQrid(QrCodeRequestDTO requestDTO) {
        QrCodeResponseDTO  responseDTO = new QrCodeResponseDTO();
        try {
            responseDTO = qrCodeServiceBiz.getCustomerByQrid(requestDTO);
        } catch (Throwable throwable) {
            log.info("getQrCodeInfo exception:",throwable);
            if(throwable instanceof WbSysException){
                WbSysException wbSysException = (WbSysException) throwable;
                responseDTO.setErrCode(wbSysException.getDefineCode());
                responseDTO.setErrMsg(wbSysException.getMessage());
            }
            responseDTO.setErrCode(ErrorCode.SYSTEM_EXCEPTION);
            responseDTO.setStatus(ResponseStatus.FAILURE);
            return responseDTO;
        }
        return responseDTO;
    }
}