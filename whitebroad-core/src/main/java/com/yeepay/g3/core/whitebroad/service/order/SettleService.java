package com.yeepay.g3.core.whitebroad.service.order;

import com.yeepay.g3.core.whitebroad.entity.order.SettleEntity;
import com.yeepay.g3.facade.whitebroad.enumtype.SettleTypeEnum;
import com.yeepay.g3.facade.whitebroad.exception.WbSysException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description: 结算服务层接口
 * Author: jiawen.huang
 * Date: 16/9/21
 * Time: 16:21
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface SettleService {

	/**
	 * 创建
	 *
	 * @param entity
	 * @return
	 */
	SettleEntity createAndUpdate(SettleEntity entity);

	/**
	 * 根据商户编号和结算出款日期查询结算(T1的和S0的)
	 *
	 * @param customerNumber
	 * @param settleDateStart
	 * @param settleDateEnd
	 * @return
	 * @throws WbSysException
	 */
	List<SettleEntity> findCuzSettleByDate(String customerNumber, Date settleDateStart, Date settleDateEnd, SettleTypeEnum settleTypeEnum);

	/**
	 * 指定行数(分页)查询结算，不会抛出异常
	 *
	 * @param customerNumber
	 * @param settleDateStart
	 * @param settleDateEnd
	 * @param fromIndex
	 * @param pageSize
	 * @return
	 */
	List<SettleEntity> findCuzSettleByDate(String customerNumber, SettleTypeEnum settleTypeEnum, String settleDateStart, String settleDateEnd,
										   int fromIndex, int pageSize);

	/**
	 * 查询远程（易宝）结算信息
	 *
	 * @param ypCustomerNo 易宝商户编号
	 * @param settleDate
	 * @return
	 */
	Map<String, Object> findRemoteSettle(String ypCustomerNo, Date settleDate);

	/**
	 * 计数
	 *
	 * @param startDate
	 * @param endDate
	 * @param settleType
	 * @param customerNumber
	 * @return
	 */
	int findTotalCount(String startDate, String endDate, String settleType, String customerNumber);

	/**
	 * 结算记总
	 *
	 * @param startDate
	 * @param endDate
	 * @param settleType
	 * @param customerNumber
	 * @return
	 */
	BigDecimal getTotalSum(String startDate, String endDate, String settleType, String customerNumber);
}
