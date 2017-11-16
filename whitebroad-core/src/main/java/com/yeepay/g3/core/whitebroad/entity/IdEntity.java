package com.yeepay.g3.core.whitebroad.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:序列化领域模型基类
 * Author: jiawen.huang
 * Date: 15/2/4
 * Time: 11:52
 * Version: 1.0
 * Copyright © 2015 YeePay.com All rights reserved.
 */
public abstract class IdEntity implements Serializable {

	protected static final long serialVersionUID = -7277949963127751206L;

	protected Long id;

	protected Date createTime;

	protected Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
