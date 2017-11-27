/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core;

import com.google.gson.Gson;
import com.shentu.g3.facade.whitebroad.dto.cashier.CashierParam;
import com.shentu.g3.facade.whitebroad.dto.opr.OprOrderParamRequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
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
@ContextConfiguration(locations = {"classpath*:/SpringContext/spring-hessianClient-test.xml"})
public class GsonTest {

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
        System.out.println();
        System.out.println("mpJson:"+new Gson().toJson(map));
    }
    @Test
    public void testGetSign(){
        CashierParam param = new CashierParam();
        param.setMerchantNo("10040011560");
        System.out.println(param.getSingString());
        Field[] files = param.getClass().getDeclaredFields();
//        for(Field field:files){
//            field.setAccessible(true);
//            try {
//                System.out.println(field.getType().equals(long.class));
//                System.out.println(" field :  "+field.getName()+"  value:"+field.get(param)+" type:"+field.getType());
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
    }



}