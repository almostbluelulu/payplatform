package com.shentu.g3.core.whitebroad.service.impl;

import com.shentu.g3.core.whitebroad.entity.qrcode.QrCode;
import com.shentu.g3.core.whitebroad.util.Constant;
import com.shentu.g3.core.whitebroad.util.SequenceUtils;
import com.shentu.g3.core.whitebroad.repository.QrCodeDao;
import com.shentu.g3.core.whitebroad.service.qrcode.QrCodeService;
import com.shentu.g3.core.whitebroad.util.security.AES;
import com.shentu.g3.facade.whitebroad.enumtype.qrcode.Status;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QrCodeServiceImpl implements QrCodeService {

    @Resource
    private QrCodeDao qrCodeDao;

    public void insert(QrCode pojo){
        try {
            long sequence = qrCodeDao.getSequence();
            String qrId = SequenceUtils.createQrCodeSequence(sequence);
            String signString = AES.encryptToBase64(qrId, Constant.STR_AES_KEY);
            pojo.setQrUrl(Constant.QRURL_HOST+qrId+"&sign="+signString);
            pojo.setQrId(qrId);
            qrCodeDao.insert(pojo);
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.CREATE_FAILED,e);
        }
    }

    public void insertList(List<QrCode> pojos){
        try {
            qrCodeDao.insertList(pojos);
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.CREATE_FAILED,e);
        }
    }

    public void update(QrCode pojo){
        try {
            qrCodeDao.update(pojo);
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.UPDATE_FAILED,e);
        }
    }

    @Override
    public List<QrCode> getActiveQrByCustomer(String customerNumber) {
        List<QrCode> qrCodeList;
        try {
            qrCodeList = qrCodeDao.getActiveQrByCustomer(customerNumber,Status.ACTIVE);
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.SELECT_EXCEPTION,e);
        }
        return qrCodeList;
    }

    @Override
    public QrCode getQrCodeByID(String qrID) {
        QrCode qrCode;
        try {
            qrCode = qrCodeDao.getQrCodeByID(qrID);
            if(null == qrCode){
                throw new WbSysException(ErrorCode.QUERY_QRCODEINFO_EXCEPTION);
            }
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.SELECT_EXCEPTION,e);
        }
        return qrCode;
    }
}
