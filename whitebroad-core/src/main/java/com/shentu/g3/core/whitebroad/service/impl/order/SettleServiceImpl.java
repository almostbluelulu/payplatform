package com.shentu.g3.core.whitebroad.service.impl.order;

import com.google.common.collect.Maps;
import com.shentu.g3.core.whitebroad.util.CheckUtils;
import com.shentu.g3.core.whitebroad.entity.order.SettleEntity;
import com.shentu.g3.core.whitebroad.repository.SettleDao;
import com.shentu.g3.core.whitebroad.service.order.SettleService;
import com.shentu.g3.core.whitebroad.util.DateUtils;
import com.shentu.g3.core.whitebroad.util.RemoteFacadeFactory;
import com.shentu.g3.facade.whitebroad.enumtype.ExternalSystem;
import com.shentu.g3.facade.whitebroad.enumtype.SettleTypeEnum;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description: 结算服务层接口实现
 * Author: jiawen.huang
 * Date: 2017/9/20
 * Time: 14:49
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Service
public class SettleServiceImpl implements SettleService {

	@Autowired
	private SettleDao settleDao;

	@Override
	public SettleEntity createAndUpdate(SettleEntity entity) {
		try {
			CheckUtils.notEmpty(entity.getSettleType(), "settleType");
			if (null != entity.getId()) {
				settleDao.update(entity);
			} else {
				settleDao.insert(entity);
			}
			return entity;
		} catch (Exception e) {
			throw new WbSysException(ErrorCode.CREATE_FAILED);
		}
	}

	@Override
	public List<SettleEntity> findCuzSettleByDate(String customerNumber, Date settleDateStart, Date settleDateEnd, SettleTypeEnum settleTypeEnum) {
		List<SettleEntity> list = settleDao.findCuzSettleByDate(customerNumber, 0, 0, settleTypeEnum == null ? null : settleTypeEnum.getValue(),
				DateUtils.getShortDateStr(settleDateStart), DateUtils.getShortDateStr(settleDateEnd));
		if (list != null && list.size() > 0) {
			return list;
		} else {
			throw new WbSysException(ErrorCode.SETTLE_NOT_EXIST);
		}
	}

	@Override
	public List<SettleEntity> findCuzSettleByDate(String customerNumber, SettleTypeEnum settleTypeEnum, String settleDateStart, String settleDateEnd, int fromIndex, int pageSize) {
		return settleDao.findCuzSettleByDate(customerNumber, fromIndex, pageSize, settleTypeEnum == null ? null : settleTypeEnum.getValue(), settleDateStart, settleDateEnd);
	}

	@Override
	public Map<String, Object> findRemoteSettle(String ypCustomerNo, Date settleDate) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("merchant_no", ypCustomerNo);
		params.put("remit_status", "SUCCESS");
		params.put("page_index", 1);
		params.put("page_size", 20);
		params.put("start_date", DateUtils.getReqDate(DateUtils.getDayStart(settleDate)));
		params.put("end_date", DateUtils.getReqDate(DateUtils.getDayEnd(settleDate)));
		Map<String, Object> resultMap = (Map<String, Object>) RemoteFacadeFactory.getYopService(ExternalSystem.SETTLE, params);
		if (!resultMap.get("status").equals("SUCCESS")) {
			throw new WbSysException(ErrorCode.SETTLE_REMOTE_EXCEPTION);
		}
		return resultMap;
	}

	@Override
	public int findTotalCount(String startDate, String endDate, String settleType, String customerNumber) {
		Integer count = settleDao.findTotalCount(startDate, endDate, settleType, customerNumber);
		return count == null ? 0 : count.intValue();
	}

	@Override
	public BigDecimal getTotalSum(String startDate, String endDate, String settleType, String customerNumber) {
		BigDecimal sum = settleDao.getTotalSum(startDate, endDate, settleType, customerNumber);
		return sum == null ? new BigDecimal(0) : sum;
	}
}
