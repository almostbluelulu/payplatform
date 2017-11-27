package com.shentu.g3.facade.whitebroad.dto;

import java.util.List;

/**
 * Description: 推送查询响应32
 * Author: jiawen.huang
 * Date: 2017/10/18
 * Time: 18:58
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class PushListResponse extends BaseResponse {

	/**
	 * 笔数
	 */
	private int msgCount;

	/**
	 * 总页数
	 */
	private int totalPage;

	/**
	 * 明细
	 */
	private List<PushMsgDTO> msgList;

	public int getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(int msgCount) {
		this.msgCount = msgCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<PushMsgDTO> getMsgList() {
		return msgList;
	}

	public void setMsgList(List<PushMsgDTO> msgList) {
		this.msgList = msgList;
	}
}
