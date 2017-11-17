/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core;
/**
 * 类名称: QrCodeTest <br>
 * 类描述: <br>
 *
 * @author: xxxx.xxx
 * @since: 17/9/23 下午5:41
 * @version: 1.0.0
 */

import com.google.gson.Gson;
import com.shentu.g3.core.whitebroad.BaseTest;
import com.shentu.g3.core.whitebroad.entity.qrcode.QrCode;
import com.shentu.g3.core.whitebroad.service.qrcode.QrCodeService;
import com.shentu.g3.facade.whitebroad.dto.qrCode.QrCodeRequestDTO;
import com.shentu.g3.facade.whitebroad.dto.qrCode.QrCodeResponseDTO;
import com.shentu.g3.facade.whitebroad.enumtype.qrcode.Status;
import com.shentu.g3.facade.whitebroad.facade.QrCodeFacade;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

/**
 * Created by dongxulu on 17/9/23.
 */
public class QrCodeTest extends BaseTest {
    @Autowired
    QrCodeService qrCodeService;
    @Autowired
    QrCodeFacade qrCodeFacade;
    @Rollback(false)
    @Test
    public void insert(){
        QrCode qrCode = new QrCode();
        qrCode.setCustomerNumber("10040011560");
        qrCode.setStatus(Status.ACTIVE);
        qrCodeService.insert(qrCode);

    }

    @Test
    public void selectByCustomer(){
        List<QrCode> qrCodeList  = qrCodeService.getActiveQrByCustomer("10040011560");
        for(QrCode qrCode:qrCodeList){
            System.out.println("#####qrCodeList"+qrCode.getQrUrl());
        }

    }
    @Test
    @Rollback(false)
    public void getQrcodeInfo(){
        QrCodeRequestDTO requestDTO = new QrCodeRequestDTO();
        requestDTO.setUserNumber("311234567845");
        requestDTO.setCustomerNumber("10040048483");
        QrCodeResponseDTO responseDTO = qrCodeFacade.getQrCodeInfo(requestDTO);
        System.out.println("<><><><><><><><>>>>>>>:"+new Gson().toJson(responseDTO));
        System.out.println("<><><><><><><><>>>>>>>:"+responseDTO.getQrCodeInfoDTOList().get(0).getQrUrl());
    }
}