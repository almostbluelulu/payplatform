package com.yeepay.g3.core.whitebroad.service;

import com.yeepay.g3.core.whitebroad.entity.WbUserTokenEntity;

/**
 * 
 * @ClassName: WbUserTokenService 
 * @Description: 用户登录token service
 * @author yunpeng.pan
 * @date 2017年9月18日 下午2:42:33 
 *
 */
public interface WbUserTokenService {

	/**
	 * 
	 * @Description: 查询用户token信息
	 * @param @param userToken
	 * @param @return 
	 * @return WbUserTokenEntity 返回类型 
	 * @author yunpeng.pan
	 * @date 2017年9月18日 下午8:38:35
	 */
	WbUserTokenEntity selectUserToken(String userToken);

	/**
	 * 根据userNo创建token，已经有token且没过期情况下，刷新过期时间
	 *
	 * @param userNo
	 * @param imei
	 * @return
	 */
	WbUserTokenEntity generateTokenByUserNo(String userNo, String imei);

	/**
	 * 根据userToken刷新token，userToken过期或者user没有token抛出异常
	 *
	 * @param userToken
	 * @return
	 */
	WbUserTokenEntity refreshToken(String userToken);

	/**
	 * 将当前tk和用户解绑注销,tk有问题抛出异常
	 *
	 * @param userToken
	 * @return
	 */
	void revokeToken(String userToken);
}