package com.yeepay.g3.core.whitebroad.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: WbUserToken 
 * @Description: 用户登录token
 * @author yunpeng.pan
 * @date 2017年9月18日 下午2:42:33 
 *
 */
public class WbUserTokenEntity implements Serializable {

	private static final long serialVersionUID = -553205879085855053L;
	
	/** 主键id */
	private Long id;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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