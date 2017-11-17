package com.shentu.g3.core.whitebroad.repository;

import com.shentu.g3.core.whitebroad.entity.trade.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDao {
    int insert(@Param("pojo") Payment pojo);

    int insertList(@Param("pojos") List<Payment> pojo);

    int update(@Param("pojo") Payment pojo);

    Payment selectByOrderID(@Param("orderID") String orderID, @Param("trxType") String trxType);

    long getSequence();

    Payment selectByYeepayOrderID(@Param("yeepayOrderId") String yeepayOrderId);
}
