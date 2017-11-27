/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core;

import com.google.gson.Gson;
import com.shentu.g3.core.whitebroad.BaseTest;
import com.shentu.g3.core.whitebroad.entity.trade.Order;
import com.shentu.g3.core.whitebroad.service.trade.OrderService;
import com.shentu.g3.facade.whitebroad.enumtype.trx.ScanType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * @ClassName: OrderServiceTest
 * @Description: OrderServiceTest
 * @author: dongxulu
 * @date: 17/9/20 下午6:29
 * @version: 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceTest extends BaseTest{
    @Autowired
    OrderService orderService;
    @Rollback(false)
    @Test
    public void insert(){
        Order order = new Order();
        String requestNo = "wb"+System.currentTimeMillis();
        System.out.println("#### requestNo:"+requestNo);
        order.setQrID("qrTest");
        order.setRequestNo(requestNo);
        order.setCustomerNumber("10040011560");
        order.setTrxAmt(new BigDecimal(0.01));
        order.setScanType(ScanType.PAY_QR);
        order = orderService.insert(order);
        System.out.println("### order:"+new Gson().toJson(order));

    }
    @Rollback(false)
    @Test
    public void update(){
        Order order = new Order();
        order.setId("wb11234567893");
        String requestNo = "wb"+System.currentTimeMillis();
        System.out.println("#### requestNo:"+requestNo);
        order.setQrID("qrTestUPdate");
        order.setRequestNo(requestNo);
        order.setCustomerNumber("10040011560");
        order.setTrxAmt(new BigDecimal(0.01));
        order.setScanType(ScanType.PAY_QR);
        orderService.update(order);
        System.out.println("### order:"+new Gson().toJson(order));

    }
    @Test
    public void select(){
        Order order = orderService.findbyID("wb11234567893");
        System.out.println("### order:"+new Gson().toJson(order));

    }
}