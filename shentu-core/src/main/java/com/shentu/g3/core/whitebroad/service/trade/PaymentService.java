/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.core.whitebroad.service.trade;

import com.shentu.g3.core.whitebroad.entity.trade.Payment;
import com.shentu.g3.facade.whitebroad.enumtype.trx.TrxType;

import java.util.List;

/**
 * @ClassName: PaymentService
 * @Description: PaymentService
 * @author: dongxulu
 * @date: 17/9/19 上午11:24
 * @version: 1.0.0
 */
public interface PaymentService {

    public void insert(Payment pojo);

    public void insertList(List<Payment> pojos);

    public void update(Payment pojo);

    public Payment selectByOrderID(String orderID, TrxType trxType);

    Payment selectByYeepayOrderID(String yeepayOrderId);

}