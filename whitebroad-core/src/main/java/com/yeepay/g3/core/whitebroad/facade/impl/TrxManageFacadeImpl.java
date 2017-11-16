package com.yeepay.g3.core.whitebroad.facade.impl;


import com.yeepay.g3.core.whitebroad.biz.TradeServiceBiz;
import com.yeepay.g3.facade.whitebroad.dto.ResponseStatus;
import com.yeepay.g3.facade.whitebroad.dto.opr.*;
import com.yeepay.g3.facade.whitebroad.dto.qrCode.QrCodeRequestDTO;
import com.yeepay.g3.facade.whitebroad.exception.ErrorCode;
import com.yeepay.g3.facade.whitebroad.exception.WbSysException;
import com.yeepay.g3.facade.whitebroad.facade.TrxManageFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: TrxManageFacadeImpl
 * @Description: TrxManageFacadeImpl
 * @author: dongxulu
 * @date: 17/9/19 下午3:11
 * @version: 1.0.0
 */
@Service("trxManageFacade")
public class TrxManageFacadeImpl implements TrxManageFacade {
    Log log = LogFactory.getLog(TrxManageFacadeImpl.class);
    @Autowired
    private TradeServiceBiz tradeServiceBiz;
    @Override
    public TrxResponseDTO scanPay(TrxRequestDTO requestDTO) {

        TrxResponseDTO responseDTO = new TrxResponseDTO();
        try {
            responseDTO = tradeServiceBiz.createOrder(requestDTO);
        } catch (Throwable throwable) {
            log.info("createOrder exception:",throwable);
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
    public TrxResponseDTO deskQrPay(QrCodeRequestDTO requestDTO) {
        TrxResponseDTO responseDTO = new TrxResponseDTO();
        try {
            responseDTO = tradeServiceBiz.deskQrPay(requestDTO);
        } catch (Throwable throwable) {
            log.info("createOrder exception:",throwable);
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
    public GetCashierUrlResponseDTO getCashierUrl(GetCashierUrlRequestDTO requestDTO) {

        GetCashierUrlResponseDTO responseDTO = null;
        try {
            responseDTO = tradeServiceBiz.getCashierUrl(requestDTO);
        } catch (Throwable throwable) {
            if(null == responseDTO){
                responseDTO = new GetCashierUrlResponseDTO();
            }
            log.error("getCashierUrl error",throwable);
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
    public OprCallBackResponseParam payCallBack(OprPayCallBackRequestParam oprPayCallBackParam) {
        OprCallBackResponseParam oprCallBackResponseParam = new OprCallBackResponseParam();
        try {
            tradeServiceBiz.payCallBackManager(oprPayCallBackParam);
        } catch (Exception e) {
            log.error("payCallBackManager error",e);
            if(e instanceof WbSysException){
                WbSysException wbSysException = (WbSysException) e;
                oprCallBackResponseParam.setErrCode(wbSysException.getDefineCode());
                oprCallBackResponseParam.setErrMsg(wbSysException.getMessage());
            }
            oprCallBackResponseParam.setManageStatus(ResponseStatus.FAILURE.toString());
            oprCallBackResponseParam.setStatus(ResponseStatus.FAILURE);
            return  oprCallBackResponseParam;
        }
        return oprCallBackResponseParam;
    }

    @Override
    public OprCallBackResponseParam csCallBack(OprCSCallBackRequestParam oprCSCallBackRequestParam) {
        OprCallBackResponseParam oprCallBackResponseParam = new OprCallBackResponseParam();
        try {
            tradeServiceBiz.csCallBackManager(oprCSCallBackRequestParam);
        } catch (Exception e) {
            log.error("csCallBackManager error",e);
            if(e instanceof WbSysException){
                WbSysException wbSysException = (WbSysException) e;
                oprCallBackResponseParam.setErrCode(wbSysException.getDefineCode());
                oprCallBackResponseParam.setErrMsg(wbSysException.getMessage());
            }
            oprCallBackResponseParam.setManageStatus(ResponseStatus.FAILURE.toString());
            oprCallBackResponseParam.setStatus(ResponseStatus.FAILURE);
            return  oprCallBackResponseParam;
        }
        return oprCallBackResponseParam;

    }

}