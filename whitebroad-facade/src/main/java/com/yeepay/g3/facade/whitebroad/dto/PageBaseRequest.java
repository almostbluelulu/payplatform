package com.yeepay.g3.facade.whitebroad.dto;

import com.yeepay.g3.facade.whitebroad.exception.ErrorCode;
import com.yeepay.g3.facade.whitebroad.exception.WbSysException;
import org.apache.commons.lang.StringUtils;

/**
 * Description: 分页查询请求父类
 * Author: jiawen.huang
 * Date: 2017/9/21
 * Time: 16:56
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class PageBaseRequest extends BaseRequest {

	protected int pageIndex = 1;//前端不传默认

	protected int pageSize = 10;//前端不传默认

	protected String startDate;

	protected String endDate;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void checkParams() {
		if (StringUtils.isBlank(this.getUserNumber())
				|| StringUtils.isBlank(this.getStartDate()) || StringUtils.isBlank(this.getEndDate())) {
			throw new WbSysException(ErrorCode.PARAM_EXCEPTION);
		}
		if (Long.valueOf(this.getEndDate().replaceAll("-", "")) <
				Long.valueOf(this.getStartDate().replaceAll("-", ""))) {
			throw new WbSysException(ErrorCode.DATE_PARAM_EXCEPTION);
		}
	}
}
