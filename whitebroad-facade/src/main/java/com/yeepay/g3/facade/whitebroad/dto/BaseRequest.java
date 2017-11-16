package com.yeepay.g3.facade.whitebroad.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Description: facade请求对象基类
 * Author: jiawen.huang
 * Date: 16/9/14
 * Time: 11:54
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class BaseRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 流水号
	 */
	protected String requestNo;

	/**
	 * 版本号
	 */
	protected String versionId;

	/**
	 * 手机号
	 */
	protected String phoneNumber;

	/**
	 * 用户号
	 */
	protected String userNumber;

	/**
	 * 手机设备IMEI号
	 */
	protected String imei;

	/**
	 * 登陆坐标
	 */
	protected String location;

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
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

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
