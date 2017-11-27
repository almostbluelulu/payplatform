package com.shentu.g3.core.whitebroad.facade;

import com.shentu.g3.core.whitebroad.BaseTest;
import com.shentu.g3.core.whitebroad.task.AsyncPushTask;
import com.shentu.g3.facade.whitebroad.dto.BaseRequest;
import com.shentu.g3.facade.whitebroad.dto.BaseResponse;
import com.shentu.g3.facade.whitebroad.dto.ResponseStatus;
import com.shentu.g3.facade.whitebroad.dto.user.*;
import com.shentu.g3.facade.whitebroad.facade.WbUserFacade;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * Description: 接口测试
 * Author: jiawen.huang
 * Date: 2017/9/27
 * Time: 12:05
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class UserFacadeTest extends BaseTest {

	@Autowired
	private WbUserFacade wbUserFacade;

	@Rollback(false)
	@Test
	public void sendRegisterSms() {
		SendRegisterSmsRequest request = new SendRegisterSmsRequest();
        request.setPhoneNumber("15900000020");
        BaseResponse response = wbUserFacade.sendRegisterSms(request);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

	@Rollback(false)
	@Test
	public void verifyRegisterSms() {
		VerifyRegisterSmsRequest request = new VerifyRegisterSmsRequest();
        request.setPhoneNumber("15900000018");
        request.setPwd("123qwe");
        request.setSmsCode("610393");
        BaseResponse response = wbUserFacade.verifyRegisterSms(request);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}


	@Rollback(false)
	@Test
	public void login() {
		LoginRequest request = new LoginRequest();
		request.setPhoneNumber("18011112222");
		request.setPwd("123qwe");
		LoginResponse response = wbUserFacade.login(request);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

	@Rollback(false)
	@Test
	public void logout() {
		LogoutReuqest reuqest = new LogoutReuqest();
		reuqest.setUserNumber("311234567802");
		reuqest.setToken("9938ff29cfd28b6586e0bc6a44cdb4f7");
		BaseResponse response = wbUserFacade.logout(reuqest);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

	@Rollback(false)
	@Test
	public void refreshTK() {
		RefreshTKRequest request = new RefreshTKRequest();
		request.setToken("9938ff29cfd28b6586e0bc6a44cdb4f7");
		request.setUserNumber("311234567802");
		RefreshTKResponse response = wbUserFacade.refreshTK(request);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

	@Rollback(false)
	@Test
	public void changePwd() {
		ChangePwdRequest request = new ChangePwdRequest();
		request.setUserNumber("311234567802");
		request.setPwd("123qwe");
		request.setNewPwd("123dd4qwer");
		BaseResponse response = wbUserFacade.changePwd(request);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

	@Rollback(false)
	@Test
	public void findPwdBySms() {
		BaseRequest request = new BaseRequest();
		request.setPhoneNumber("18011112222");
		BaseResponse response = wbUserFacade.findPwdBySms(request);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

	@Rollback(false)
	@Test
	public void resetPwdBySms() {
		ResetPwdRequest request = new ResetPwdRequest();
		request.setPhoneNumber("18011112222");
		request.setNewPwd("1234qwer");
		request.setSmsCode("851285");
		BaseResponse response = wbUserFacade.resetPwdBySms(request);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

	@Autowired
	private AsyncPushTask asyncPushTask;

	@Test
	public void testAsync() {

		asyncPushTask.pushOpenMsg2APP("311234567802");
		while (true) {

		}
	}


}
