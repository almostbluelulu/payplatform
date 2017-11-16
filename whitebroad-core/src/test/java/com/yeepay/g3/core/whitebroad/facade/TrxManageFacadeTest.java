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
import com.yeepay.g3.facade.whitebroad.dto.opr.OprCallBackResponseParam;
import com.yeepay.g3.facade.whitebroad.dto.opr.OprPayCallBackRequestParam;
import com.yeepay.g3.facade.whitebroad.dto.opr.TrxRequestDTO;
import com.yeepay.g3.facade.whitebroad.dto.opr.TrxResponseDTO;
import com.yeepay.g3.facade.whitebroad.enumtype.trx.PayType;
import com.yeepay.g3.facade.whitebroad.enumtype.trx.ScanType;
import com.yeepay.g3.facade.whitebroad.facade.TrxManageFacade;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dongxulu on 17/10/12.
 */
public class TrxManageFacadeTest extends BaseTest {
    TrxManageFacade trxManageFacade;

    @Before
    public void init(){
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        try {
            trxManageFacade = (TrxManageFacade) hessianProxyFactory.create(TrxManageFacade.class, "http://172.18.163.142:8080/whitebroad-hessian/hessian/TrxManageFacade");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void getUrl(){
            Map<String,String> map = new HashMap<String, String>();
            map.put("goodsName","白板测试商品");
            map.put("goodsDesc","白板商品描述");
            String customerNumber = "10040048483";
            TrxRequestDTO requestDTO = new TrxRequestDTO();
            requestDTO.setUserNumber("311234567845");
            requestDTO.setMerchantNo(customerNumber);
            requestDTO.setPayType(PayType.WECHAT);
            requestDTO.setUserNumber("311234567802");
            requestDTO.setGoodsName("白板测试商品");
            requestDTO.setGoodsDesc("白板商品描述");
            String accessOrder = ""+System.nanoTime();
            System.out.println("###request_order "+accessOrder);
            requestDTO.setMerchantOrderId(accessOrder);
            requestDTO.setOrderAmount("0.01");
            requestDTO.setParentMerchantNo("10040040565");
            requestDTO.setScanType(ScanType.DESK_QR);
            TrxResponseDTO trxResponseDTO = trxManageFacade.scanPay(requestDTO);
            System.out.println("###### pay url:"+trxResponseDTO.getPayUrl());
            System.out.println("###### result DTO:"+new Gson().toJson(trxResponseDTO));
    }

    @Test
    public void payCallBack(){
        OprPayCallBackRequestParam requestParam  = new OprPayCallBackRequestParam();
        requestParam.setOrderAmount("0.01");
        requestParam.setPayAmount("0.01");
        requestParam.setPaySuccessDate("2017-10-11 10:10:10");
        requestParam.setStatus("SUCCESS");
        requestParam.setRequestDate("2017-10-11 10:10:10");
        requestParam.setUniqueOrderNo("1001201710120000000000035600");
        requestParam.setMerchantNo("10040048483");
        requestParam.setOrderId("1507801661127967000");
        OprCallBackResponseParam responseParam = trxManageFacade.payCallBack(requestParam);
        System.out.println("######>>>>>>>>>>>> result:"+new Gson().toJson(responseParam));
    }
}