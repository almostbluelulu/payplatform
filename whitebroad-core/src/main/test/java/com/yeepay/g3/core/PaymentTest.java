/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core;

import com.google.gson.Gson;
import com.yeepay.g3.core.whitebroad.BaseTest;
import com.yeepay.g3.core.whitebroad.entity.trade.Order;
import com.yeepay.g3.core.whitebroad.entity.trade.Payment;
import com.yeepay.g3.core.whitebroad.service.trade.OrderService;
import com.yeepay.g3.core.whitebroad.service.trade.PaymentService;
import com.yeepay.g3.facade.whitebroad.enumtype.trx.TrxType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName: PaymentTest
 * @Description: PaymentTest
 * @author: dongxulu
 * @date: 17/9/23 下午3:29
 * @version: 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentTest extends BaseTest{
    @Autowired
    PaymentService paymentService;
    @Autowired
    OrderService orderService;
    @Rollback(false)
    @Test
    public void insert(){
        Order order = orderService.findbyID("wb11234567893");
        Payment payment = new Payment();
        payment.setTrxAmt(order.getTrxAmt());
        payment.setOrder_id("ceshiID1123123");
        payment.setCustomerNumber(order.getCustomerNumber());
        payment.setTrxType(TrxType.PURCHASE);
        payment.setCardNumber("6220110299928228");
        paymentService.insert(payment);
    }

    @Test
    public void select(){
      Payment  payment = paymentService.selectByOrderID("wb11234567893",TrxType.PURCHASE);
        System.out.println("##### payment :"+new Gson().toJson(payment));
    }
    @Rollback(false)
    @Test
    public void update(){
        Payment  payment = paymentService.selectByOrderID("wb11234567893",TrxType.PURCHASE);
        payment.setBankName("东旭银行测试");
        payment.setCardNumber("6002112000145111");
        paymentService.update(payment);
    }


}