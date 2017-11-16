package com.yeepay.g3.core.whitebroad.facade.impl;

import org.springframework.stereotype.Service;

import com.yeepay.g3.facade.whitebroad.dto.BaseRequest;
import com.yeepay.g3.facade.whitebroad.dto.BaseResponse;
import com.yeepay.g3.facade.whitebroad.dto.user.*;
import com.yeepay.g3.facade.whitebroad.facade.WbUserFacade;

/**
 * Description: 用户基础业务接口
 * Author: jiawen.huang
 * Date: 2017/9/26
 * Time: 12:00
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Service("wbUserFacade")
public class WbUserFacadeImpl extends AbstractFacade implements WbUserFacade {


	@Override
	public BaseResponse sendRegisterSms(SendRegisterSmsRequest requestDTO) {
		return wbUserBiz.sendRegisterSms(requestDTO);
	}

	@Override
	public BaseResponse verifyRegisterSms(VerifyRegisterSmsRequest requestDTO) {
		return wbUserBiz.verifyRegisterSms(requestDTO);
	}

	@Override
	public LoginResponse login(LoginRequest requestDTO) {
		return wbUserBiz.login(requestDTO);
	}

	@Override
	public BaseResponse logout(LogoutReuqest reuqest) {
		return wbUserBiz.logout(reuqest);
	}

	@Override
	public RefreshTKResponse refreshTK(RefreshTKRequest request) {
		return wbUserBiz.refreshTK(request);
	}

	@Override
	public BaseResponse changePwd(ChangePwdRequest request) {
		return wbUserBiz.changePwd(request);
	}

	@Override
	public BaseResponse findPwdBySms(BaseRequest request) {
		return wbUserBiz.findPwdBySms(request);
	}

	@Override
	public BaseResponse resetPwdBySms(ResetPwdRequest request) {
		return wbUserBiz.resetPwdBySms(request);
	}
}
