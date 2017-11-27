package com.shentu.g3.core.whitebroad.biz.impl;

import com.shentu.g3.core.whitebroad.biz.AbstractBiz;
import com.shentu.g3.core.whitebroad.biz.WbUserBiz;
import com.shentu.g3.core.whitebroad.entity.UserEntity;
import com.shentu.g3.core.whitebroad.entity.WbUserTokenEntity;
import com.shentu.g3.core.whitebroad.parser.impl.LoginResponseConvert;
import com.shentu.g3.core.whitebroad.parser.impl.RefreshTokenDTOConvert;
import com.shentu.g3.core.whitebroad.entity.SmsCodeEntity;
import com.shentu.g3.facade.whitebroad.dto.BaseRequest;
import com.shentu.g3.facade.whitebroad.dto.BaseResponse;
import com.shentu.g3.facade.whitebroad.dto.user.*;
import com.shentu.g3.facade.whitebroad.enumtype.ControlTypeEnum;
import com.shentu.g3.facade.whitebroad.enumtype.SmsTypeEnum;
import com.shentu.g3.facade.whitebroad.enumtype.UserStatus;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;
import org.springframework.stereotype.Component;

/**
 * Description: 用户基础业务层
 * Author: jiawen.huang
 * Date: 2017/9/19
 * Time: 10:22
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class WbUserBizImpl extends AbstractBiz implements WbUserBiz {

	@Override
	public BaseResponse sendRegisterSms(SendRegisterSmsRequest request) {
		//未注册检查和初始化用户
		UserEntity userEntity = userService.create(request.getPhoneNumber());
		//检查短信发送频率控制
		securityControlService.checkFreeze(userEntity.getPhoneNumber(), ControlTypeEnum.SMS_SEND_CONTROL);
		//生成本次二维码
		SmsCodeEntity smsCodeEntity = smsCodeService.getAvaliable(request.getPhoneNumber(), SmsTypeEnum.REGISTER);
		//发送二维码
		notifyService.sendSmsRandom(request.getPhoneNumber(), smsCodeEntity.getSmsCode(), smsCodeEntity.getSmsType());
		//增加发送次数控制
		securityControlService.increaseCount(request.getPhoneNumber(), ControlTypeEnum.SMS_SEND_CONTROL);
		userEntity.setUserStatus(UserStatus.SEND);
		userService.update(userEntity);
		return new BaseResponse();
	}

	@Override
	public BaseResponse verifyRegisterSms(VerifyRegisterSmsRequest request) {
		//检查没注册
		UserEntity userEntity = userService.findUnRegisterByPhone(request.getPhoneNumber());
		//验证验证码
		verifySMS(request.getPhoneNumber(), request.getSmsCode(), SmsTypeEnum.REGISTER);
		//更新User
		userEntity.setUserStatus(UserStatus.REGISTER);
		userEntity.setUserPwd(request.getPwd());
		userService.update(userEntity);
		return new BaseResponse();
	}

	@Override
	public LoginResponse login(LoginRequest request) {
		//检查用户注册状态和密码
		UserEntity userEntity = userService.checkPwd(request.getPhoneNumber(), request.getPwd());
		//生成tk
		WbUserTokenEntity userTokenEntity = wbUserTokenService.generateTokenByUserNo(userEntity.getUserNumber(), request.getImei());
		return new LoginResponseConvert().convert2DTO(userEntity, userTokenEntity);
	}

	@Override
	public BaseResponse logout(LogoutReuqest request) {
		//获取注册用户
		UserEntity userEntity = userService.findRegisterByUserNo(request.getUserNumber());
		//移除tk
		wbUserTokenService.revokeToken(request.getToken());
		return new BaseResponse();
	}

	@Override
	public RefreshTKResponse refreshTK(RefreshTKRequest request) {
		//获取注册用户
		UserEntity userEntity = userService.findRegisterByUserNo(request.getUserNumber());
		//刷新tk
		WbUserTokenEntity userTokenEntity = wbUserTokenService.refreshToken(request.getToken());
		return new RefreshTokenDTOConvert().convert2DTO(userTokenEntity);
	}

	@Override
	public BaseResponse changePwd(ChangePwdRequest request) {
		try {
			//获取用户
			UserEntity userEntity = userService.findRegisterByUserNo(request.getUserNumber());
			securityControlService.checkFreeze(userEntity.getPhoneNumber(), ControlTypeEnum.RESET_PWD_CONTROL);
			userService.checkPwd(userEntity.getPhoneNumber(), request.getPwd());
			userEntity.setUserPwd(request.getNewPwd());
			userService.update(userEntity);
		} catch (WbSysException e) {
			if (e.getDefineCode().equals(ErrorCode.USER_LOGIN_PWD_ERROR)) {
				securityControlService.increaseCount(request.getPhoneNumber(), ControlTypeEnum.RESET_PWD_CONTROL);
				throw new WbSysException(ErrorCode.USER_PWD_ERROR);//转义时从USER_LOGIN_PWD_ERROR改为USER_PWD_ERROR
			}
			throw e;
		}

		return new BaseResponse();
	}

	@Override
	public BaseResponse findPwdBySms(BaseRequest request) {
		//获取用户
		UserEntity userEntity = userService.findRegisterByPhone(request.getPhoneNumber());
		//检查找回密码权限是否被冻结
		securityControlService.checkFreeze(userEntity.getPhoneNumber(), ControlTypeEnum.FIND_PWD_CONTROL);
		//生成找回密码短信
		SmsCodeEntity smsCodeEntity = smsCodeService.getAvaliable(request.getPhoneNumber(), SmsTypeEnum.FIND_LOGIN_PWD);
		//发送找回密码短信
		notifyService.sendSmsRandom(request.getPhoneNumber(), smsCodeEntity.getSmsCode(), smsCodeEntity.getSmsType());
		//增加找回密码次数
		securityControlService.increaseCount(request.getPhoneNumber(), ControlTypeEnum.FIND_PWD_CONTROL);
		return new BaseResponse();
	}

	@Override
	public BaseResponse resetPwdBySms(ResetPwdRequest request) {
		//获取用户
		UserEntity userEntity = userService.findRegisterByPhone(request.getPhoneNumber());
		//检查sms code
		verifySMS(request.getPhoneNumber(), request.getSmsCode(), SmsTypeEnum.FIND_LOGIN_PWD);
		//重置密码
		userEntity.setUserPwd(request.getNewPwd());
		userService.update(userEntity);
		return new BaseResponse();
	}

	private void verifySMS(String phoneNo, String smsCode, SmsTypeEnum smsTypeEnum) {
		try {
			securityControlService.checkFreeze(phoneNo, ControlTypeEnum.VERIFY_SMS_CONTROL);
			smsCodeService.verify(phoneNo, smsTypeEnum, smsCode);
		} catch (WbSysException e) {
			if (e.getDefineCode().equals(ErrorCode.SMS_VERIFY_ERROR)) {
				securityControlService.increaseCount(phoneNo, ControlTypeEnum.VERIFY_SMS_CONTROL);
			}
			throw e;
		}
	}
}
