/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core.whitebroad.biz;

import com.yeepay.g3.core.whitebroad.BaseTest;
import com.yeepay.g3.core.whitebroad.entity.trade.Order;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @ClassName: OprServiceBizTest
 * @Description: OprServiceBizTest
 * @author: dongxulu
 * @date: 17/9/21 下午4:19
 * @version: 1.0.0
 */
public class OprServiceBizTest extends BaseTest {
    @Autowired
    OprServiceBiz oprServiceBiz;
    @Test
    public  void getUrl(){

        Order order = new Order();
        order.setCreateTime(new Date());

        oprServiceBiz.getPayUrl(order);

    }


}