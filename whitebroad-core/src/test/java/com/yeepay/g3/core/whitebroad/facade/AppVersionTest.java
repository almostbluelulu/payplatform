package com.yeepay.g3.core.whitebroad.facade;

import com.yeepay.g3.core.whitebroad.BaseTest;
import com.yeepay.g3.facade.whitebroad.dto.user.AppVersionRequset;
import com.yeepay.g3.facade.whitebroad.dto.user.AppVersionResponse;
import com.yeepay.g3.facade.whitebroad.facade.AppVersionFacade;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 2017/10/17
 * Time: 15:31
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class AppVersionTest extends BaseTest {

	@Autowired
	private AppVersionFacade appVersionFacade;

	@Test
	public void test() {
		AppVersionRequset requset = new AppVersionRequset();
		requset.setVersionId("AP17101777189533");
		AppVersionResponse response = appVersionFacade.checkNew(requset);
		System.out.println(response);
	}
}
