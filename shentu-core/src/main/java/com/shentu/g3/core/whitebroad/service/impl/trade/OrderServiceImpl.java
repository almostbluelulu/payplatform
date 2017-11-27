package com.shentu.g3.core.whitebroad.service.impl.trade;

import com.shentu.g3.core.whitebroad.entity.trade.Order;
import com.shentu.g3.core.whitebroad.repository.OrderDao;
import com.shentu.g3.core.whitebroad.service.trade.OrderService;
import com.shentu.g3.core.whitebroad.util.SequenceUtils;
import com.shentu.g3.facade.whitebroad.enumtype.BizPrefixEnum;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    public Order insert(Order pojo){
        //@TODO 此处生成主键 写入
        Order order = null;
        try {
            Long sequence = orderDao.getSequence();
            pojo.setId(BizPrefixEnum.WB_TRX.getValue()+ SequenceUtils.createOrderSequence(sequence));
            orderDao.insert(pojo);
            order = orderDao.findByID(pojo.getId());
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.DUPLICATE_KEY_EXCEPTION,e);
        }
        return order;
    }


    public int insertList(List<Order> pojos){
        int result;
        try {
            result =  orderDao.insertList(pojos);
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.DUPLICATE_KEY_EXCEPTION,e);
        }
        return result;
    }

    public int update(Order pojo){
        int result;
        try {
            result = orderDao.update(pojo);
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.UPDATE_FAILED,e);
        }
        return result;
    }

    @Override
    public Order findbyID(String id) {
        Order order;
        try {
            order  = orderDao.findByID(id);
            if(null == order){
                throw new WbSysException(ErrorCode.ORDER_UNKNOW_EXCEPTION);
            }
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.ORDER_EXCEPTION,e);
        }
        return order;
    }

    @Override
    public Order findbyRequestNo(String requestNo,String customerNumber) {
        Order order;
        try {
            order =orderDao.findByRequestNo(requestNo,customerNumber);
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.ORDER_EXCEPTION,e);
        }
        return order;
    }

	@Override
	public List<Order> selectReceiptList(String id, String startDate, String endDate, String orderStatus, String payType,
										 String userNumber, int fromIndex, int pageSize) {
		return orderDao.selectReceiptList(id, startDate, endDate, orderStatus, payType, userNumber, fromIndex, pageSize);
	}


	@Override
	public int getOrderTotalCount(String id, String startDate, String endDate, String orderStatus, String payType, String userNumber) {
		Integer count = orderDao.getOrderTotalCount(id, startDate, endDate, orderStatus, payType, userNumber);
		return count == null ? 0 : count.intValue();
	}

    @Override
    public long getSequnce() {
        return orderDao.getSequence();
    }

    @Override
    public Order findByYeepayId(String yeepayid) {
        Order order;
        try {
            order =orderDao.findByYeepayId(yeepayid);
            if(null == order){
                throw new WbSysException(ErrorCode.ORDER_UNKNOW_EXCEPTION);
            }
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.ORDER_EXCEPTION,e);
        }
        return order;
    }
}
