package com.shentu.g3.app.pay.dto;

import com.shentu.g3.app.pay.enums.ResponseStatus;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @ClassName: BaseResponse
 * @Description: BaseResponse
 * @author: dongxulu
 * @date: 17/9/28 下午6:45
 * @version: 1.0.0
 */
public class BaseResponse implements Serializable {

	private static final long serialVersionUID = -2723223172568560669L;
	/**
	 * 错误码
	 */
	protected String errCode;

	/**
	 * 错误信息
	 */
	protected String errMsg;

	/**
	 * 返回状态
	 */
	private ResponseStatus status = ResponseStatus.SUCCESS;

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
