package com.shentu.g3.app.pay.http;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

/**
 * Description: 易钱包客户端请求解密后,封装成新的HttpServletRequest
 * Author: jiawen.huang
 * Date: 16/4/25
 * Time: 18:59
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class WFrontRequest extends HttpServletRequestWrapper {

	private Map customParamsMap;

	private String redirectURI;

	public WFrontRequest(HttpServletRequest request) {
		super(request);
	}

	public void setCustomParamsMap(Map customParamsMap) {
		this.customParamsMap = customParamsMap;
	}

	public Map getCustomParamsMap() {
		return customParamsMap;
	}

	public String getRedirectURI() {
		return redirectURI;
	}

	public void setRedirectURI(String redirectURI) {
		this.redirectURI = redirectURI;
	}

	public String getParameter(String s) {
		String[] arr = s != null ? (String[]) this.customParamsMap.get(s) : null;
		return arr != null && arr.length > 0 ? arr[0] : null;
	}

	public Enumeration getParameterNames() {
		return Collections.enumeration(this.customParamsMap.keySet());
	}

	public String[] getParameterValues(String s) {
		String value = (String) this.getCustomParamsMap().get(s);
		String[] result = new String[]{value};
		return result;
	}

	public Map getParameterMap() {
		return this.getCustomParamsMap();
	}

	public String getRequestURI() {
		return this.getRedirectURI();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
