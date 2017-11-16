/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core;
/**
 * 类名称: TrxmanageTest <br>
 * 类描述: <br>
 *
 * @author: xxxx.xxx
 * @since: 17/9/26 下午4:42
 * @version: 1.0.0
 */

import com.google.gson.Gson;
import com.yeepay.g3.core.whitebroad.BaseTest;
import com.yeepay.g3.core.whitebroad.biz.OprServiceBiz;
import com.yeepay.g3.core.whitebroad.service.trade.OrderService;
import com.yeepay.g3.core.whitebroad.util.RemoteFacadeFactory;
import com.yeepay.g3.core.whitebroad.util.security.Md5Utils;
import com.yeepay.g3.facade.whitebroad.dto.opr.*;
import com.yeepay.g3.facade.whitebroad.enumtype.ExternalSystem;
import com.yeepay.g3.facade.whitebroad.enumtype.trx.ScanType;
import com.yeepay.g3.facade.whitebroad.facade.TrxManageFacade;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.HashMap;
import java.util.Map;

public class TrxmanageTest extends BaseTest {

    @Autowired
    private TrxManageFacade trxManageFacade;
    @Autowired
    private OprServiceBiz oprServiceBiz;
    @Autowired
    private OrderService orderService;
    @Test
    @Rollback(false)
    public void testScanCode(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("goodsName","白板测试商品");
        map.put("goodsDesc","白板商品描述");
        String customerNumber = "10040048483";
        TrxRequestDTO requestDTO = new TrxRequestDTO();
        requestDTO.setUserNumber("311234567802");
        requestDTO.setGoodsName("白板测试商品");
        requestDTO.setGoodsDesc("白板商品描述");
        String accessOrder = ""+System.nanoTime();
        System.out.println("###request_order "+accessOrder);
        requestDTO.setMerchantOrderId(accessOrder);
        requestDTO.setOrderAmount("0.01");
        requestDTO.setParentMerchantNo("10040040565");
        requestDTO.setScanType(ScanType.DESK_QR);
        TrxResponseDTO responseDTO = trxManageFacade.scanPay(requestDTO);
        System.out.println("########>>>>>>>>>:"+responseDTO.getPayUrl());

    }
    @Test
    public void getPayUrl(){
        GetCashierUrlRequestDTO  requestDTO =  new GetCashierUrlRequestDTO();
        requestDTO.setOrderId("wbtrx11234567806");
        GetCashierUrlResponseDTO responseDTO  = trxManageFacade.getCashierUrl(requestDTO);
        System.out.println("####"+responseDTO.getPayUrl());
        System.out.println("####"+new Gson().toJson(responseDTO));
    }

    @Test
    public void trxTest(){
        Map<String, Object> paramsMap = new HashMap<>();
        String customerNumber = "10040048483";
        paramsMap.put("parentMerchantNo", "10040040565");
        paramsMap.put("merchantNo",customerNumber);
        Map result = (Map) RemoteFacadeFactory.getYopService(ExternalSystem.QRUERY_MERCHANT_KEY,paramsMap);
        String merchatKey = (String)result.get("merHmacKey");
        if(StringUtils.isEmpty(merchatKey)){
            System.out.println("####result QRUERY_MERCHANT_KEY: "+new Gson().toJson(result));
            return;
        }
        System.out.println("merchatKey  :"+merchatKey);
        Map<String,String> map = new HashMap<String, String>();
        map.put("goodsName","白板测试商品");
        map.put("goodsDesc","白板商品描述");
        OprOrderParamRequestDTO requestDTO = new OprOrderParamRequestDTO();
        requestDTO.setMerchantNo(customerNumber);
        requestDTO.setOrderAmount("0.01");
        requestDTO.setCsUrl("");
        requestDTO.setFundProcessType("");
        requestDTO.setGoodsParamExt(new Gson().toJson(map));
        requestDTO.setIndustryParamExt("");
        requestDTO.setMemo("");
        requestDTO.setNotifyUrl("http://qa.yeepay.com/ymf-pay/");
        String accessOrder = ""+System.nanoTime();
        System.out.println("###request_order "+accessOrder);
        requestDTO.setOrderId(accessOrder);
        requestDTO.setParentMerchantNo("10040040565");
        requestDTO.setRedirectUrl("");
        StringBuilder hmacBuilder = new StringBuilder();
        hmacBuilder.append("parentMerchantNo=").append(requestDTO.getParentMerchantNo()).append("&");
        hmacBuilder.append("merchantNo=").append(requestDTO.getMerchantNo()).append("&");
        hmacBuilder.append("orderId=").append(requestDTO.getOrderId()).append("&");
        hmacBuilder.append("orderAmount=").append(requestDTO.getOrderAmount()).append("&");
        hmacBuilder.append("notifyUrl=").append(requestDTO.getNotifyUrl());
        String srchmac = hmacBuilder.toString();

        System.out.println("#####srcHmac:"+srchmac);
        String hmac = Md5Utils.encoderHmacSha256(srchmac,merchatKey);
        System.out.println("#####hmac:"+hmac);
        Map<String,Object> params = null;
        try {
            params = oprServiceBiz.getOprParam(requestDTO);
            params.put("hmac",hmac);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("PARMMAP----->:"+new Gson().toJson(params));
        Map resultMap = (Map) RemoteFacadeFactory.getYopService(ExternalSystem.OPR_SYS,params);
        System.out.println("result:"+new Gson().toJson(resultMap));
    }

}