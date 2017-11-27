package com.shentu.g3.facade.whitebroad.dto;

import java.io.Serializable;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 2017/10/18
 * Time: 20:18
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class PushMsgDTO implements Serializable {

	private static final long serialVersionUID = -342879674453712775L;

	/**
	 * 消息编号
	 */
	private String messageNo;

	/**
	 * 极光编号
	 */
	private String jpushId;

	/**
	 * 消息发布者
	 */
	private String operator;

	/**
	 * 消息类型
	 */
	private String type;

	/**
	 * 消息标题
	 */
	private String title;

	/**
	 * 消息内容
	 */
	private String content;

	/**
	 * 业务生效时间
	 */
	private String lifeStart;

	/**
	 * 业务失效时间
	 */
	private String lifeEnd;

	/**
	 * 网页/图片链接1
	 */
	private String url1;

	/**
	 * 网页/图片链接2
	 */
	private String url2;

	/**
	 * 推送时间
	 */
	private String pushTime;

	public String getMessageNo() {
		return messageNo;
	}

	public void setMessageNo(String messageNo) {
		this.messageNo = messageNo;
	}

	public String getJpushId() {
		return jpushId;
	}

	public void setJpushId(String jpushId) {
		this.jpushId = jpushId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLifeStart() {
		return lifeStart;
	}

	public void setLifeStart(String lifeStart) {
		this.lifeStart = lifeStart;
	}

	public String getLifeEnd() {
		return lifeEnd;
	}

	public void setLifeEnd(String lifeEnd) {
		this.lifeEnd = lifeEnd;
	}

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public String getPushTime() {
		return pushTime;
	}

	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}
}
