package com.shentu.g3.app.pay.http;


import com.shentu.g3.app.pay.exception.WFSysException;
import com.shentu.g3.app.pay.util.DataConvertUtil;
import com.shentu.g3.app.pay.util.WFAuthUtil;
import com.yeepay.g3.facade.whitebroad.exception.WbSysException;

/**
 * Description: 白板前置响应对象
 * Author: jiawen.huang
 * Date: 16/4/22
 * Time: 18:19
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class WFrontResponse {

	private Status status;

	private Object data;
	
	public WFrontResponse() {
		super();
	}

	public WFrontResponse(String code, String msg) {
		super();
		Status status = new Status();
		status.setCode(code);
		status.setMsg(msg);
		this.status = status;
	}

	public static WFrontResponse newResultInstance(Object result) {
		WFrontResponse response = new WFrontResponse();
		Status status = new Status();
		if(result instanceof WbSysException) {
			WbSysException wbException = (WbSysException)result;
			status.setCode(wbException.getDefineCode());
			status.setMsg(wbException.getMessage());
		} else if (result instanceof Throwable) {
			status.setCode("10001");
			status.setMsg("系统异常");
		} else {
			status.setCode("0000");
			status.setMsg("success");
			response.setData(result);
		}
		response.setStatus(status);
		return response;
	}
	
	/**
	 * 通过返回对象创建一个已加密的响应
	 * <p> 调用newInstance方法前应该打印一条LOG日志 </p>
	 *
	 * @param data 返回参数
	 * @return
	 */
	public static String newInstance(Object data) {
		WFrontResponse response = new WFrontResponse();
		response.setData(data);
		Status status = new Status();
		status.setCode("0000");
		status.setMsg("success");
		response.setStatus(status);
		return WFAuthUtil.encrypt(DataConvertUtil.toJson(response));
	}

	/**
	 * 通过异常自动创建一个已加密的响应
	 * <p> 调用newInstance方法前应该打印一条LOG日志 </p>
	 *
	 * @param exception 异常
	 * @return
	 */
	public static String newInstance(WFSysException exception) {
		WFrontResponse response = new WFrontResponse();
		Status status = new Status();
		status.setCode(exception.getDefineCode());
		status.setMsg(exception.getMessage());
		response.setStatus(status);
		return WFAuthUtil.encrypt(DataConvertUtil.toJson(response));
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static class Status {

		public String code;

		public String msg;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
}
