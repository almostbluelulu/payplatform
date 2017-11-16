package com.yeepay.g3.core.whitebroad.service.impl;

import com.yeepay.g3.core.whitebroad.entity.UserEntity;
import com.yeepay.g3.core.whitebroad.repository.UserDao;
import com.yeepay.g3.core.whitebroad.service.UserService;
import com.yeepay.g3.core.whitebroad.util.BizUidUtil;
import com.yeepay.g3.facade.whitebroad.enumtype.BizPrefixEnum;
import com.yeepay.g3.facade.whitebroad.enumtype.UserStatus;
import com.yeepay.g3.facade.whitebroad.exception.ErrorCode;
import com.yeepay.g3.facade.whitebroad.exception.WbSysException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: 用户类服务层
 * Author: jiawen.huang
 * Date: 2017/9/18
 * Time: 20:05
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;


	@Override
	public UserEntity create(String phoneNumber) {
		UserEntity userEntity = userDao.findByPhoneNo(phoneNumber);
		if (null != userEntity) {
			if (userEntity.getUserStatus().equals(UserStatus.REGISTER)) {
				throw new WbSysException(ErrorCode.USER_HAS_REGISTER);//注册完成
			} else {
				return userEntity;
			}
		} else {
			userEntity = new UserEntity();
			userEntity.setPhoneNumber(phoneNumber);
			userEntity.setUserNumber(BizUidUtil.generateBizUID(BizPrefixEnum.USER_PR.getValue(), userDao.getSequence()));
			userEntity.setUserStatus(UserStatus.INIT);
			try {
				userDao.insert(userEntity);
			} catch (DataIntegrityViolationException e) {
				throw new WbSysException(ErrorCode.USER_CREATE_EXCEPTION, e);
			}
		}
		return userEntity;
	}

	@Override
	public UserEntity findByPhoneNo(String phoneNo) {
		UserEntity userEntity = userDao.findByPhoneNo(phoneNo);
		if (null == userEntity) {
			throw new WbSysException(ErrorCode.USER_NOT_EXIST);
		}
		return userEntity;
	}

	@Override
	public UserEntity findByUserNo(String userNo) {
		UserEntity userEntity = userDao.findByUserNo(userNo);
		if (null == userEntity) {
			throw new WbSysException(ErrorCode.USER_NOT_EXIST);
		}
		return userEntity;
	}

	@Override
	public UserEntity findRegisterByUserNo(String userNo) {
		UserEntity userEntity = findByUserNo(userNo);
		if (!userEntity.getUserStatus().equals(UserStatus.REGISTER)) {
			throw new WbSysException(ErrorCode.USER_UN_REGISTER);
		}
		return userEntity;
	}

	@Override
	public UserEntity findRegisterByPhone(String phoneNo) {
		UserEntity userEntity = findByPhoneNo(phoneNo);
		if (!userEntity.getUserStatus().equals(UserStatus.REGISTER)) {
			throw new WbSysException(ErrorCode.USER_UN_REGISTER);
		}
		return userEntity;
	}

	@Override
	public UserEntity findUnRegisterByPhone(String phoneNo) {
		UserEntity userEntity = userDao.findByPhoneNo(phoneNo);
		if (null != userEntity) {
			if (userEntity.getUserStatus().equals(UserStatus.REGISTER)) {
				throw new WbSysException(ErrorCode.USER_HAS_REGISTER);//注册完成
			} else {
				return userEntity;
			}
		} else {
			throw new WbSysException(ErrorCode.USER_UNSEND_SMS);
		}
	}

	@Override
	public UserEntity checkPwd(String phoneNo, String pwd) {
		UserEntity userEntity = findRegisterByPhone(phoneNo);
		if (userEntity.getUserPwd().equals(pwd)) {
			return userEntity;
		}
		throw new WbSysException(ErrorCode.USER_LOGIN_PWD_ERROR);
	}

	@Override
	public UserEntity update(UserEntity userEntity) {
		Integer num = userDao.update(userEntity);
		if (0 == num) {
			throw new WbSysException(ErrorCode.USER_NOT_EXIST);

		}
		return userEntity;
	}

	@Override
	public List<UserEntity> list() {
		List<UserEntity> list = userDao.list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			throw new WbSysException(ErrorCode.USER_NOT_EXIST);
		}
	}
}
