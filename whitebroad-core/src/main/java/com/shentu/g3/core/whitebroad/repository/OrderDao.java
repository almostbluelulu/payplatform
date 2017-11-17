package com.shentu.g3.core.whitebroad.repository;

import com.shentu.g3.core.whitebroad.entity.trade.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {

    int insert(@Param("pojo") Order pojo);

    int insertSelective(@Param("pojo") Order pojo);

    int insertList(@Param("pojos") List<Order> pojo);

    int update(@Param("pojo") Order pojo);

    Order findByID(@Param("id") String id);

    Order findByRequestNo(@Param("requestNo") String requestNo,@Param("customerNumber") String customerNumber);

    Long getSequence();

	List<Order> selectReceiptList(@Param("id") String id, @Param("startDate") String startDate,
								  @Param("endDate") String endDate, @Param("orderStatus") String orderStatus,
								  @Param("payType") String payType, @Param("userNumber") String userNumber,
								  @Param("fromIndex") int fromIndex, @Param("pageSize") int pageSize);

	Integer getOrderTotalCount(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("orderStatus") String orderStatus, 
			@Param("payType") String payType, @Param("userNumber") String userNumber);
    Order findByYeepayId(@Param("yeepayid") String yeepayid);
    Integer getOrderTotalCount(@Param("id") String id, @Param("startDate") String startDate,
                               @Param("endDate") String endDate, @Param("orderStatus") String orderStatus,
                               @Param("payType") String payType, @Param("userNumber") String userNumber);

}
