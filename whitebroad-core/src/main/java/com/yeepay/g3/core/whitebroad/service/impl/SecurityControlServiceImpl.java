package com.yeepay.g3.core.whitebroad.service.impl;


import com.yeepay.g3.core.whitebroad.entity.SecurityControlEntity;
import com.yeepay.g3.core.whitebroad.repository.SecurityControlDao;
import com.yeepay.g3.core.whitebroad.service.SecurityControlService;
import com.yeepay.g3.facade.whitebroad.enumtype.ControlTypeEnum;
import com.yeepay.g3.facade.whitebroad.exception.WbSysException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Description: 业务权限控制服务层实现
 * Author: jiawen.huang
 * Date: 16/9/21
 * Time: 16:26
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class SecurityControlServiceImpl implements SecurityControlService {

	@Autowired
	private SecurityControlDao securityControlDao;

	@Override
	public void checkFreeze(String phoneNumber, ControlTypeEnum controlTypeEnum) {
		SecurityControlEntity securityControlEntity = securityControlDao.findByPhoneNumber(phoneNumber, controlTypeEnum);
		if (null != securityControlEntity) {
			if (this.checkFreezeInterval(securityControlEntity, controlTypeEnum)) {
				//超过了冻结时间，清空错误记录
				securityControlEntity.setMistakeTimes(0);
				securityControlEntity.setFirstMistakeTime(null);
				securityControlEntity.setLastMistakeTime(null);
				securityControlEntity.setFreezed(false);
				securityControlDao.update(securityControlEntity);
			} else {
				//查看是否到了最大错误次数
				if (securityControlEntity.getMistakeTimes() >= securityControlEntity.getControlTypeEnum().getTimes()) {
					securityControlEntity.setFreezed(true);
					securityControlDao.update(securityControlEntity);
					throw new WbSysException(controlTypeEnum.getErrorCode());
				}
			}
		}
	}

	@Override
	public void increaseCount(String phoneNumber, ControlTypeEnum controlTypeEnum) {
		SecurityControlEntity securityControlEntity = securityControlDao.findByPhoneNumber(phoneNumber, controlTypeEnum);
		Date date = new Date();
		if (null == securityControlEntity) {
			securityControlEntity = new SecurityControlEntity();
			securityControlEntity.setPhoneNumber(phoneNumber);
			securityControlEntity.setControlTypeEnum(controlTypeEnum);
			securityControlEntity.setFirstMistakeTime(date);
			securityControlEntity.setMistakeTimes(1);
			securityControlEntity.setLastMistakeTime(date);
			securityControlEntity.setFreezed(Boolean.FALSE);
			securityControlDao.save(securityControlEntity);
		} else {
			int count = securityControlEntity.getMistakeTimes();
			if (count == 0) {
				securityControlEntity.setFirstMistakeTime(date);
			}
			count++;
			securityControlEntity.setMistakeTimes(count);
			securityControlEntity.setLastMistakeTime(date);
			securityControlEntity.setFreezed(Boolean.FALSE);
			securityControlDao.update(securityControlEntity);
		}
	}

	public Boolean checkFreezeInterval(SecurityControlEntity securityControlEntity, ControlTypeEnum controlTypeEnum) {
		Long freezeInterval = controlTypeEnum.getInterval();
		Date now = new Date();
		Long intervalTime = ((now == null ? 0 : now.getTime())
				- (securityControlEntity.getLastMistakeTime() == null ? 0 : securityControlEntity.getLastMistakeTime().getTime())) / 1000;
		return (intervalTime >= freezeInterval);
	}
}
