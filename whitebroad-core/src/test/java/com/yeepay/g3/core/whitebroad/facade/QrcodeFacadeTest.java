/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core.whitebroad.facade;
/**
 * 类名称: TrxManageFacadeTest <br>
 * 类描述: <br>
 *
 * @author: xxxx.xxx
 * @since: 17/10/12 下午2:25
 * @version: 1.0.0
 */

import com.caucho.hessian.client.HessianProxyFactory;
import com.google.gson.Gson;
import com.yeepay.g3.core.whitebroad.BaseTest;
import com.yeepay.g3.facade.whitebroad.dto.qrCode.QrCodeRequestDTO;
import com.yeepay.g3.facade.whitebroad.dto.qrCode.QrCodeResponseDTO;
import com.yeepay.g3.facade.whitebroad.facade.QrCodeFacade;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;

/**
 * Created by dongxulu on 17/10/12.
 */
public class QrcodeFacadeTest extends BaseTest {
    QrCodeFacade qrCodeFacade;

    @Before
    public void init(){
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        try {
            qrCodeFacade = (QrCodeFacade) hessianProxyFactory.create(QrCodeFacade.class, "http://localhost:8080/whitebroad-hessian/hessian/QrCodeFacade");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getQrCodeInfo(){
        QrCodeRequestDTO qrCodeRequestDTO = new QrCodeRequestDTO();
        qrCodeRequestDTO.setUserNumber("311234567851");
        QrCodeResponseDTO responseDTO = qrCodeFacade.getQrCodeInfo(qrCodeRequestDTO);
        System.out.println("####responseDTO "+new Gson().toJson(responseDTO));
    }
    @Test
    public void getQrCodeInfoById(){
        QrCodeRequestDTO qrCodeRequestDTO = new QrCodeRequestDTO();
        qrCodeRequestDTO.setQrId("8812345674");
        QrCodeResponseDTO responseDTO = qrCodeFacade.getCustomerByQrid(qrCodeRequestDTO);
        System.out.println("####responseDTO "+new Gson().toJson(responseDTO));
    }
}