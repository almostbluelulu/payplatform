package com.yeepay.g3.facade.whitebroad.facade;

import com.yeepay.g3.facade.whitebroad.dto.BaseRequest;
import com.yeepay.g3.facade.whitebroad.dto.BaseResponse;
import com.yeepay.g3.facade.whitebroad.dto.user.*;

/**
 * Description: 用户基础业务接口
 * Author: jiawen.huang
 * Date: 2017/9/26
 * Time: 11:59
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface WbUserFacade {

	/**
	 * 发送注册短信验证码
	 *
	 * @param requestDTO
	 * @return
	 */
	BaseResponse sendRegisterSms(SendRegisterSmsRequest requestDTO);

	/**
	 * 验证注册短信验证码并且注册
	 *
	 * @param requestDTO
	 * @return
	 */
	BaseResponse verifyRegisterSms(VerifyRegisterSmsRequest requestDTO);

	/**
	 * 用户登陆
	 *
	 * @param requestDTO
	 * @return
	 */
	LoginResponse login(LoginRequest requestDTO);

	/**
	 * 注销
	 *
	 * @param reuqest
	 * @return
	 */
	BaseResponse logout(LogoutReuqest reuqest);

	/**
	 * 刷新tk
	 *
	 * @return
	 */
	RefreshTKResponse refreshTK(RefreshTKRequest request);

	/**
	 * 修改密码
	 *
	 * @param request
	 * @return
	 */
	BaseResponse changePwd(ChangePwdRequest request);

	/**
	 * 发送找回密码短信
	 *
	 * @param request
	 * @return
	 */
	BaseResponse findPwdBySms(BaseRequest request);

	/**
	 * 找回密码验证短信和重置
	 *
	 * @param request
	 * @return
	 */
	BaseResponse resetPwdBySms(ResetPwdRequest request);
}
