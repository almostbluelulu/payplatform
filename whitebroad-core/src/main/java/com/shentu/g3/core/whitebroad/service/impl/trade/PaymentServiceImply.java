package com.shentu.g3.core.whitebroad.service.impl.trade;

import com.shentu.g3.core.whitebroad.service.trade.PaymentService;
import com.shentu.g3.core.whitebroad.entity.trade.Payment;
import com.shentu.g3.core.whitebroad.repository.OrderDao;
import com.shentu.g3.core.whitebroad.repository.PaymentDao;
import com.shentu.g3.core.whitebroad.util.SequenceUtils;
import com.shentu.g3.facade.whitebroad.enumtype.BizPrefixEnum;
import com.shentu.g3.facade.whitebroad.enumtype.trx.TrxType;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaymentServiceImply implements PaymentService {

    @Resource
    private PaymentDao paymentDao;
    @Resource
    private OrderDao orderDao;

    public void insert(Payment pojo){
        try {
            Payment payment = paymentDao.selectByOrderID(pojo.getOrder_id(),pojo.getTrxType().toString());
            if(null !=payment){
                return;
            }
            Long sequence = paymentDao.getSequence();
            pojo.setId(BizPrefixEnum.WB_PAY.getValue()+ SequenceUtils.createOrderSequence(sequence));
            paymentDao.insert(pojo);
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.DUPLICATE_KEY_EXCEPTION,e);
        }

    }
    public void insertList(List<Payment> pojos){
        try {
            paymentDao.insertList(pojos);
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.DUPLICATE_KEY_EXCEPTION,e);
        }
    }

    public void update(Payment pojo){

        try {
            paymentDao.update(pojo);
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.UPDATE_FAILED,e);

        }
    }

    @Override
    public Payment selectByOrderID(String orderID , TrxType trxType) {
        Payment payment;
        try {
            payment = paymentDao.selectByOrderID(orderID,TrxType.PURCHASE.toString());
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.SELECT_EXCEPTION,e);
        }
        return payment;
    }

    @Override
    public Payment selectByYeepayOrderID(String yeepayOrderId) {
        Payment payment;
        try {
            payment =  paymentDao.selectByYeepayOrderID(yeepayOrderId);
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.SELECT_EXCEPTION,e);
        }
        return payment;
    }
}
