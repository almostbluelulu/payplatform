/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core;

import com.google.gson.Gson;
import com.yeepay.g3.core.whitebroad.BaseTest;
import com.yeepay.g3.core.whitebroad.util.Constant;
import com.yeepay.g3.core.whitebroad.util.security.AES;
import com.yeepay.g3.facade.whitebroad.dto.opr.OprOrderParamRequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 类名称: GsonTest <br>
 * 类描述: <br>
 *
 * @author: xxxx.xxx
 * @since: 17/9/20 下午1:37
 * @version: 1.0.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class GsonTest extends BaseTest{

    @Test
    public void testJson(){
        Map<String, Object> params = new HashMap<String, Object>();

        OprOrderParamRequestDTO requestDTO = new OprOrderParamRequestDTO();
        requestDTO.setMerchantNo("10040011560");
        requestDTO.setCsUrl("http://localhost:8080/hessian");
        requestDTO.setOrderAmount("0.01");
        String objJson = new Gson().toJson(requestDTO);
        System.out.println("objJson:"+objJson);
        Map<String,Object> map = new Gson().fromJson(objJson,LinkedHashMap.class);
        System.out.println("@@@@@@@@:"+requestDTO.getSingString());
    }

    @Test
    public void testBase64(){
        String s = "wbtrx11234567808";
        String  base64s = AES.encryptToBase64(s, Constant.STR_AES_KEY);
        System.out.println("<<<><><><>>>>>>>>>>>:"+base64s);
    }

    @Test
    public void testdecodeBase64(){
        String s = "sQXfDPXJ5B2BRAk735N35GP8iFSGGgakcBotPFlA3TI=";
        String  base64s = AES.decryptFromBase64("+6ADC8ULWFXkzQjBbw49QWP8iFSGGgakcBotPFlA3TI=", Constant.STR_AES_KEY);
        System.out.println("<<<><><><>>>>>>>>>>>:"+base64s);
    }
}