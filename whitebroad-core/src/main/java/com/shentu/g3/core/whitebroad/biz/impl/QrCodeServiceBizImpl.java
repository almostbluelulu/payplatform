/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.core.whitebroad.biz.impl;

import com.shentu.g3.core.whitebroad.biz.AbstractBiz;
import com.shentu.g3.core.whitebroad.biz.QrCodeServiceBiz;
import com.shentu.g3.core.whitebroad.entity.AccountOpenEntity;
import com.shentu.g3.core.whitebroad.entity.qrcode.QrCode;
import com.shentu.g3.facade.whitebroad.dto.ResponseStatus;
import com.shentu.g3.facade.whitebroad.dto.qrCode.QrCodeInfoDTO;
import com.shentu.g3.facade.whitebroad.dto.qrCode.QrCodeRequestDTO;
import com.shentu.g3.facade.whitebroad.dto.qrCode.QrCodeResponseDTO;
import com.shentu.g3.facade.whitebroad.enumtype.qrcode.Status;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: QrCodeServiceBizImpl
 * @Description: QrCodeServiceBizImpl
 * @author: dongxulu
 * @date: 17/9/19 上午11:27
 * @version: 1.0.0
 */
@Service
@Transactional
public class QrCodeServiceBizImpl  extends AbstractBiz implements QrCodeServiceBiz {

    @Override
    public QrCodeResponseDTO getQrCodeInfo(QrCodeRequestDTO requestDTO) {
        QrCodeResponseDTO responseDTO = new QrCodeResponseDTO();
        AccountOpenEntity accountOpenEntity = null;
        try {
            accountOpenEntity = applyService.getPayableByUserNumber(requestDTO.getUserNumber());
        } catch (Exception e) {
            if(e instanceof WbSysException){
                WbSysException ex = (WbSysException)e;
                responseDTO.setErrCode(ex.getDefineCode());
                responseDTO.setErrMsg(ex.getMessage());
            }else{
                responseDTO.setErrCode(ErrorCode.SYSTEM_EXCEPTION);
            }
            responseDTO.setStatus(ResponseStatus.FAILURE);
            return responseDTO;
        }
        requestDTO.setCustomerNumber(accountOpenEntity.getCustomerNo());
        List<QrCodeInfoDTO> qrList = new ArrayList<QrCodeInfoDTO>();
        List<QrCode> qrCodeList  = qrCodeService.getActiveQrByCustomer(requestDTO.getCustomerNumber());
        if(null ==qrCodeList||qrCodeList.size()<=0){
            qrCodeList = getNewQrCode(requestDTO);
        }
        for(QrCode qrCode:qrCodeList){
            QrCodeInfoDTO qrCodeInfoDTO = new QrCodeInfoDTO();
            qrCodeInfoDTO.setQrUrl(qrCode.getQrUrl());
            qrCodeInfoDTO.setQrId(qrCode.getQrId());
            qrCodeInfoDTO.setShopName(qrCode.getShopNumber());
            qrList.add(qrCodeInfoDTO);
        }
        responseDTO.setQrCodeInfoDTOList(qrList);
        responseDTO.setCustomerNumber(requestDTO.getCustomerNumber());
        responseDTO.setFullname(accountOpenEntity.getMerFullName());
        responseDTO.setUserNumber(accountOpenEntity.getUserNumber());
        responseDTO.setShortName(responseDTO.getShortName());
        return responseDTO;
    }

    @Override
    public QrCodeResponseDTO getCustomerByQrid(QrCodeRequestDTO requestDTO) {
        QrCodeResponseDTO responseDTO = new QrCodeResponseDTO();
        String qrid = requestDTO.getQrId();
        AccountOpenEntity accountOpenEntity = null;
        try {
            QrCode qrCode = qrCodeService.getQrCodeByID(qrid);
            responseDTO.setQrId(qrid);
            String userNumber = qrCode.getUserNumber();
            accountOpenEntity = applyService.getPayableByUserNumber(userNumber);
            } catch (Exception e) {
                if(e instanceof WbSysException){
                    WbSysException ex = (WbSysException)e;
                    responseDTO.setErrCode(ex.getDefineCode());
                    responseDTO.setErrMsg(ex.getMessage());
                }else{
                    responseDTO.setErrCode(ErrorCode.SYSTEM_EXCEPTION);
                }
                responseDTO.setStatus(ResponseStatus.FAILURE);
                return responseDTO;
            }
        responseDTO.setCustomerNumber(accountOpenEntity.getCustomerNo());
        responseDTO.setFullname(accountOpenEntity.getMerFullName());
        responseDTO.setShortName(accountOpenEntity.getMerShortName());
        responseDTO.setUserNumber(accountOpenEntity.getUserNumber());
        return responseDTO;
    }

    private List<QrCode> getNewQrCode(QrCodeRequestDTO requestDTO){
        List<QrCode> qrCodeList ;
        QrCode qrCode = new QrCode();
        qrCode.setStatus(Status.ACTIVE);
        qrCode.setCustomerNumber(requestDTO.getCustomerNumber());
        qrCode.setUserNumber(requestDTO.getUserNumber());
        qrCodeService.insert(qrCode);
        return qrCodeService.getActiveQrByCustomer(requestDTO.getCustomerNumber());
    }
}