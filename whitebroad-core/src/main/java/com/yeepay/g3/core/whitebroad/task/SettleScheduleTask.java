package com.yeepay.g3.core.whitebroad.task;


import com.yeepay.g3.core.whitebroad.biz.AbstractBiz;
import com.yeepay.g3.core.whitebroad.entity.AccountOpenEntity;
import com.yeepay.g3.core.whitebroad.entity.UserEntity;
import com.yeepay.g3.core.whitebroad.entity.order.SettleEntity;
import com.yeepay.g3.core.whitebroad.util.DateUtils;
import com.yeepay.g3.facade.whitebroad.enumtype.SettleTypeEnum;
import com.yeepay.g3.facade.whitebroad.exception.ErrorCode;
import com.yeepay.g3.facade.whitebroad.exception.WbSysException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description: 结算类定时业务
 * Author: jiawen.huang
 * Date: 2017/9/20
 * Time: 18:54
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class SettleScheduleTask extends AbstractBiz {

	/**
	 * 每天3点定时查所有商户结算信息
	 */
	@Scheduled(cron = "* 0/30 * * * ?")
	public void customerSettleToday() {
		List<UserEntity> userEntityList = userService.list();
		for (UserEntity userEntity : userEntityList) {
			BigDecimal settleAmount = new BigDecimal(0);
			BigDecimal settleFee = new BigDecimal(0);
			try {
				//检查开户
				AccountOpenEntity accountOpenEntity = applyService.getPayableByUserNumber(userEntity.getUserNumber());
				//同步远程T1
				Map<String, Object> settleMap = settleService.findRemoteSettle(accountOpenEntity.getYpCustomerNumber(), new Date());//
				if (null != settleMap && null != settleMap.get("list")) {
					SettleEntity settleEntity = new SettleEntity();
					List<SettleEntity> existList = settleService.findCuzSettleByDate(accountOpenEntity.getCustomerNo(), SettleTypeEnum.T1,
                            DateUtils.getShortDateStr(new Date()), DateUtils.getShortDateStr(new Date()), 0, 1);
                    if (null != existList && existList.size() > 0) {
						settleEntity = existList.get(0);// 每个商户每天的T1结算到账只有一条
					} else {
						settleEntity.setCustomerNumber(accountOpenEntity.getCustomerNo());
						settleEntity.setSettleType(SettleTypeEnum.T1);
						settleEntity.setSettleDate(new Date());
					}
					for (Object resultMap : ((List<Object>) settleMap.get("list"))) {
						settleAmount = settleAmount.add(new BigDecimal((Double) ((Map<String, Object>) resultMap).get("realamount")));
						settleFee = settleFee.add(new BigDecimal((Double) ((Map<String, Object>) resultMap).get("fee")));
					}
					settleEntity.setSettleAmount(settleAmount);
					settleEntity.setSettleFee(settleFee);
					settleService.createAndUpdate(settleEntity);
				}
			} catch (Throwable e) {
				if (e instanceof WbSysException) {
					WbSysException ee = (WbSysException) e;
					if (ee.getDefineCode().equals(ErrorCode.ACCOUNT_STATUS_DENY)) {
						// do nothing and don't log,case wbExp auto print
					}
				}
			}
		}
	}
}
