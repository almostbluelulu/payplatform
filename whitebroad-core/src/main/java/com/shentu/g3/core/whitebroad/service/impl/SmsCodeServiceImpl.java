package com.shentu.g3.core.whitebroad.service.impl;

import com.shentu.g3.core.whitebroad.repository.SmsCodeDao;
import com.shentu.g3.core.whitebroad.service.SmsCodeService;
import com.shentu.g3.core.whitebroad.util.Constant;
import com.shentu.g3.core.whitebroad.util.DateUtils;
import com.shentu.g3.core.whitebroad.util.RandomUtils;
import com.shentu.g3.core.whitebroad.entity.SmsCodeEntity;
import com.shentu.g3.facade.whitebroad.enumtype.SmsTypeEnum;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Description:验证码service
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 14:29
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class SmsCodeServiceImpl implements SmsCodeService {

	@Autowired
	private SmsCodeDao smsCodeDao;

	@Override
	public SmsCodeEntity getAvaliable(String phoneNo, SmsTypeEnum smsType) {
		SmsCodeEntity smsCodeEntity = smsCodeDao.findOne(phoneNo, smsType);
		if (null != smsCodeEntity && smsCodeEntity.getEffectTime().after(new Date())) {//未过期
			return smsCodeEntity;
		}
		return createOne(phoneNo, smsType);
	}

	@Override
	public void verify(String phoneNo, SmsTypeEnum smsType, String smsCode) {
		SmsCodeEntity smsCodeEntity = findOne(phoneNo, smsType);
		if (smsCode.equals(smsCodeEntity.getSmsCode())) {
			smsCodeDao.setUnAvaliable(smsCodeEntity.getId());
		} else {
			throw new WbSysException(ErrorCode.SMS_VERIFY_ERROR);
		}
	}

	/**
	 * 查询未过期且avaliable有效的
	 *
	 * @param phoneNo
	 * @param smsType
	 * @return
	 */
	private SmsCodeEntity findOne(String phoneNo, SmsTypeEnum smsType) {
		SmsCodeEntity smsCodeEntity = smsCodeDao.findOne(phoneNo, smsType);
		if (null == smsCodeEntity) {
			throw new WbSysException(ErrorCode.SMS_EXPIRED_OR_UNFIND);
		}
		return smsCodeEntity;
	}

	private SmsCodeEntity createOne(String phoneNo, SmsTypeEnum smsType) {
		SmsCodeEntity smsCodeEntity = new SmsCodeEntity();
		smsCodeEntity.setPhoneNumber(phoneNo);
		smsCodeEntity.setSmsType(smsType);
		smsCodeEntity.setCreateTime(new Date());
		smsCodeEntity.setEffectTime(DateUtils.addSecond(new Date(), Constant.SMS_CODE_EFFECT_INTERVAL));
		smsCodeEntity.setAvailable(false);
		smsCodeEntity.setSmsCode(RandomUtils.randomNumberString(6));
		smsCodeDao.save(smsCodeEntity);
		return smsCodeEntity;
	}
}
