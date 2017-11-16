package com.yeepay.g3.core.whitebroad.dao;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.report.ReceivedsResult;
import com.yeepay.g3.core.whitebroad.BaseTest;
import com.yeepay.g3.core.whitebroad.entity.PushMsgEntity;
import com.yeepay.g3.core.whitebroad.repository.PushMsgDao;
import com.yeepay.g3.core.whitebroad.service.JPushService;
import com.yeepay.g3.core.whitebroad.util.BizUidUtil;
import com.yeepay.g3.core.whitebroad.util.DateUtils;
import com.yeepay.g3.facade.whitebroad.enumtype.MsgTypeEnum;
import com.yeepay.g3.facade.whitebroad.enumtype.PushStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

/**
 * Description: AttachmentTest
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 18:09
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class PushMsgTest extends BaseTest {

	@Autowired
	private PushMsgDao pushMsgDao;

	@Autowired
	private JPushService jPushService;

	@Test
	public void result() {
		JPushClient jpushClient = new JPushClient("11a4916cbd5f705c2cfc645e", "7cd38d599c251844b6de7318");
		try {
			ReceivedsResult result = jpushClient.getReportReceiveds("2251800053476431");
			System.out.println("Got result - " + result);

		} catch (APIConnectionException e) {
			// Connection error, should retry later
			e.printStackTrace();

		} catch (APIRequestException e) {
			// Should review the error, and fix the request
			e.printStackTrace();
			System.out.println("HTTP Status: " + e.getStatus());
			System.out.println("Error Code: " + e.getErrorCode());
			System.out.println("Error Message: " + e.getErrorMessage());
		}
	}

	@Test
	public void tt() {
		PushMsgEntity pushMsgEntity = pushMsgDao.findByMessageNo("MSA17080889524774");
		jPushService.push2Customers(pushMsgEntity);
	}

	@Rollback(false)
	@Test
	public void test() {
		for (int i = 0; i < 20; i++) {
			PushMsgEntity pushMsgEntity = new PushMsgEntity();
			String no = BizUidUtil.generateBizUID("MS", pushMsgDao.nextSequence());
			pushMsgEntity.setMessageNo(no);
			pushMsgEntity.setOperator("zhenzhen.zhang");
			pushMsgEntity.setUserNumbers("311234567819");
			pushMsgEntity.setTitle("test2");
			pushMsgEntity.setType(MsgTypeEnum.SYS);
			pushMsgEntity.setPushStatus(PushStatus.SENDED);
			pushMsgEntity.setContent("测试消息nooooooooooooooooooooooo:" + i);
			pushMsgEntity.setPushTime(DateUtils.addDay(new Date(), -3));
			pushMsgDao.save(pushMsgEntity);
		}
	}

}
