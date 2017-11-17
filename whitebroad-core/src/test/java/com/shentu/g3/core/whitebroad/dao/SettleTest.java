package com.shentu.g3.core.whitebroad.dao;

import com.shentu.g3.core.whitebroad.BaseTest;
import com.shentu.g3.core.whitebroad.entity.order.SettleEntity;
import com.shentu.g3.core.whitebroad.repository.SettleDao;
import com.shentu.g3.core.whitebroad.util.RandomUtils;
import com.shentu.g3.facade.whitebroad.enumtype.SettleTypeEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;

/**
 * Description: 结算dao test
 * Author: jiawen.huang
 * Date: 2017/9/24
 * Time: 17:44
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class SettleTest extends BaseTest {

	@Autowired
	private SettleDao settleDao;

	@Rollback(false)
	@Test
	public void insert() {
		for (int i = 0; i < 30; i++) {
			SettleEntity settleEntity = new SettleEntity();
			settleEntity.setCustomerNumber("10030011560");
			settleEntity.setSettleType(SettleTypeEnum.T1);
			settleEntity.setSettleFee(new BigDecimal(0.01));
			settleEntity.setSettleAmount(new BigDecimal(RandomUtils.randomNumberString(3)));
			try {
				Thread.sleep(100l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			settleDao.insert(settleEntity);
		}

	}
}
