package com.shentu.g3.core.whitebroad.dao;

import java.util.List;

import javax.annotation.Resource;

import com.shentu.g3.core.whitebroad.BaseTest;
import org.junit.Test;

import com.shentu.g3.facade.whitebroad.dto.api.ApiInfoDTO;
import com.shentu.g3.facade.whitebroad.facade.WbApiInfoFacade;
import com.shentu.g3.facade.whitebroad.facade.WbUserAuthFacade;


/**
 * Description:
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 18:09
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class UserAuthTest extends BaseTest {

	@Resource
	private WbUserAuthFacade userAuthFacade;
	@Resource
	private WbApiInfoFacade wbApiInfoFacade;

	@Test
	public void findApiInfoByUri() {
		ApiInfoDTO d = wbApiInfoFacade.findApiInfoByUri("wbUserAuthFacade/testTransfer");
		System.out.println(d);
	}
	
	@Test
	public void queryAllApi() {
		List<ApiInfoDTO> list = wbApiInfoFacade.queryAllApi();
		System.out.println(list);
	}
	
	@Test
	public void findUserAuthInfo() {
		userAuthFacade.findUserAuthInfo("abc");
		System.out.println("ok");
	}
}
