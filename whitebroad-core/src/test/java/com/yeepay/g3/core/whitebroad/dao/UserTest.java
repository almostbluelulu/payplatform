package com.yeepay.g3.core.whitebroad.dao;

import com.yeepay.g3.core.whitebroad.BaseTest;
import com.yeepay.g3.core.whitebroad.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;


/**
 * Description: 用户测试
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 18:09
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class UserTest extends BaseTest {

	@Autowired
	private UserService userService;

	@Rollback(false)
	@Test
	public void testSave() {
		String ph = "18500001113";
		userService.create(ph);
//		for (int i = 0; i < 50; i++) {
//			ph = ph + 1;
//		}
//		PageHelper.startPage(1, 10);
	}
}
