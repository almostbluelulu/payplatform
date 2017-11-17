package com.shentu.g3.core.whitebroad.dao;

import com.shentu.g3.core.whitebroad.BaseTest;
import com.shentu.g3.core.whitebroad.repository.OrderDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * Description: 结算dao test
 * Author: jiawen.huang
 * Date: 2017/9/24
 * Time: 17:44
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class OrderTest extends BaseTest {

	@Autowired
	private OrderDao orderDao;

	@Rollback(false)
	@Test
	public void insert() {

		System.out.println(orderDao.getOrderTotalCount(null, "2017-09-01", "2017-09-30", "b",
				"a", "1"));
	}


}
