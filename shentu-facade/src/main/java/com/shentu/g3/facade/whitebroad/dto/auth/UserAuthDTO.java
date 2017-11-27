package com.shentu.g3.facade.whitebroad.dto.auth;


import java.util.Date;

import com.shentu.g3.facade.whitebroad.dto.BaseResponse;

/**
 * 
 * @ClassName: UserAuthDTO 
 * @Description: 用户鉴权DTO
 * @author yunpeng.pan
 * @date 2017年9月18日 下午8:18:29 
 *
 */
public class UserAuthDTO extends BaseResponse {


    /**
	 * 
	 */
	private static final long serialVersionUID = -5676072337941240463L;
	
	/** 电话号码 */
	private String phoneNumber;
	
	/** 用户编号 */
	private String userNumber;
	
	/** 登录token码 */
	private String token;
	
	/** 移动设备身份码 */
	private String imei;
	
	/** 刷新时间 */
	private Date refreshTime;
	
	/** 过期时间 */
	private Date expireTime;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Date getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(Date refreshTime) {
		this.refreshTime = refreshTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

}