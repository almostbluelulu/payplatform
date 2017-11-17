package com.shentu.g3.core.whitebroad.service.impl;

import com.shentu.g3.core.whitebroad.entity.WbUserTokenEntity;
import com.shentu.g3.core.whitebroad.repository.WbUserTokenDao;
import com.shentu.g3.core.whitebroad.service.WbUserTokenService;
import com.shentu.g3.core.whitebroad.util.Constant;
import com.shentu.g3.core.whitebroad.util.DateUtils;
import com.shentu.g3.core.whitebroad.util.security.Digest;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


@Service
public class WbUserTokenServiceImpl implements WbUserTokenService {
	
	@Resource
	private WbUserTokenDao wbUserTokenDao;

	public WbUserTokenEntity insert(String userNo, String imei) {
		WbUserTokenEntity entity = new WbUserTokenEntity();
		entity.setUserNumber(userNo);
		entity.setToken(generateToken(userNo));
		entity.setPhoneNumber("");
		entity.setImei(imei);
		Date now = new Date();
		entity.setExpireTime(DateUtils.addDays(now, Constant.TOKEN_KEEPALIVE_DAYS));
		entity.setRefreshTime(now);
		wbUserTokenDao.insert(entity);
		return entity;
	}
	
	public WbUserTokenEntity updateToken(WbUserTokenEntity entity) {
		entity.setToken(generateToken(entity.getUserNumber()));
		Date now = new Date();
		entity.setExpireTime(DateUtils.addDays(now, Constant.TOKEN_KEEPALIVE_DAYS));
		entity.setRefreshTime(now);
		wbUserTokenDao.updateToken(entity);
		return entity;
	}
	
	public WbUserTokenEntity updateExpireTime(WbUserTokenEntity entity) {
		Date now = new Date();
		entity.setExpireTime(DateUtils.addDays(now, Constant.TOKEN_KEEPALIVE_DAYS));
		entity.setRefreshTime(now);
		wbUserTokenDao.updateExpireTime(entity);
		return entity;
	}

	@Override
	public WbUserTokenEntity selectUserToken(String userToken) {
		return wbUserTokenDao.selectUserToken(userToken);
	}

	@Override
	public WbUserTokenEntity generateTokenByUserNo(String userNo, String imei) {
		if(StringUtils.isBlank(userNo)) {
			throw new WbSysException(ErrorCode.PARAM_EXCEPTION);
		}
		WbUserTokenEntity entity = wbUserTokenDao.selectUserTokenByUserNo(userNo);
		if(entity == null) {
			// 新用户登录，插入新记录
			return this.insert(userNo, imei);
		}
		if (StringUtils.isBlank(entity.getToken())  || 
				DateUtils.compareDate(new Date(), entity.getExpireTime(), Calendar.SECOND) > 0) {
			// token失效，更新token
			return this.updateToken(entity);
		} else {
			// 失效时间延期
			return this.updateExpireTime(entity);
		}
	}

	@Override
	public WbUserTokenEntity refreshToken(String userToken) {
		if(StringUtils.isBlank(userToken)) {
			throw new WbSysException(ErrorCode.PARAM_EXCEPTION);
		}
		WbUserTokenEntity entity = wbUserTokenDao.selectUserToken(userToken);
		if(entity == null) {
			throw new WbSysException(ErrorCode.USER_TOKEN_NOT_EXIST);
		}
		if (DateUtils.compareDate(new Date(), entity.getExpireTime(), Calendar.SECOND) > 0) {
			throw new WbSysException(ErrorCode.USER_TOKEN_INVALID);
		}
		Date now = new Date();
		entity.setExpireTime(DateUtils.addDays(now, Constant.TOKEN_KEEPALIVE_DAYS));
		entity.setRefreshTime(now);
		wbUserTokenDao.updateExpireTime(entity);
		return entity;
	}

	@Override
	public void revokeToken(String userToken) {
		if(StringUtils.isBlank(userToken)) {
			throw new WbSysException(ErrorCode.PARAM_EXCEPTION);
		}
		WbUserTokenEntity entity = wbUserTokenDao.selectUserToken(userToken);
		if(entity == null) {
			throw new WbSysException(ErrorCode.USER_TOKEN_NOT_EXIST);
		}
		entity.setToken(null);
		entity.setExpireTime(new Date());
		wbUserTokenDao.revokeToken(entity);
	}
	
	private String generateToken(String userNo) {
		String tokenStr = new Random().nextInt(3) + userNo + System.currentTimeMillis();
		return Digest.md5Digest(tokenStr);
	}

}