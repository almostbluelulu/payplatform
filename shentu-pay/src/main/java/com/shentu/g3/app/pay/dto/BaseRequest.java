package com.shentu.g3.app.pay.dto;

import java.io.Serializable;

/**
 * @ClassName: BaseRequest
 * @Description: BaseRequest
 * @author: dongxulu
 * @date: 17/9/28 下午6:45
 * @version: 1.0.0
 */
public class BaseRequest implements Serializable {


	private static final long serialVersionUID = 4737013070626269381L;
	/**
	 * 用户号
	 */
	protected String userNumber;

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
}
