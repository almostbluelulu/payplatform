package com.shentu.g3.core.whitebroad.task;

import com.google.gson.Gson;
import com.shentu.g3.core.whitebroad.biz.AbstractBiz;
import com.shentu.g3.core.whitebroad.entity.AccountOpenEntity;
import com.shentu.g3.core.whitebroad.entity.PushMsgEntity;
import com.shentu.g3.core.whitebroad.entity.trade.Payment;
import com.shentu.g3.core.whitebroad.parser.impl.PaymentDTOConvert;
import com.shentu.g3.core.whitebroad.util.Constant;
import com.shentu.g3.facade.whitebroad.dto.trade.PaymentDTO;
import com.shentu.g3.facade.whitebroad.enumtype.MsgTypeEnum;
import com.shentu.g3.facade.whitebroad.enumtype.PushStatus;
import com.shentu.g3.facade.whitebroad.enumtype.PushType;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Description: 异步发送app推送消息(不要打日志，有拦截器打日志)
 * Author: jiawen.huang
 * Date: 2017/9/22
 * Time: 15:37
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class AsyncPushTask extends AbstractBiz {

	@Resource
	private ThreadPoolTaskExecutor taskExecutor;

	/**
	 * 向商户推送支付成功订单
	 *
	 * @param payment
	 */
	public void pushPayMsg2APP(final Payment payment) {
		taskExecutor.execute(new Runnable() {
			public void run() {
				try {
					//检查账户是否开通
					AccountOpenEntity accountOpenEntity = applyService.getAccountOpenEntityByCustomerNo(payment.getCustomerNumber());
					//配置消息
					PushMsgEntity pushMsgEntity = createMsg(accountOpenEntity.getUserNumber(), getPayContentJson(payment), MsgTypeEnum.PAY);
					pushMsgService.save(pushMsgEntity);
					//推送
					pushMsg(pushMsgEntity);
				} catch (Throwable e) {
					throw new WbSysException(ErrorCode.PUSH_TASK_EXCEPTION, e);
				}
			}
		});
	}

	/**
	 * 向商户推送开户进度通知
	 *
	 * @param customerNo
	 */
	public void pushOpenMsg2APP(final String customerNo) {
		taskExecutor.execute(new Runnable() {
			public void run() {
				try {
					//检查账户
					AccountOpenEntity accountOpenEntity = applyService.getAccountOpenEntityByCustomerNo(customerNo);
					//配置消息
					String content = "您的开户状态有更新，当前状态为：" + accountOpenEntity.getAccountOpenStatus().getDisplayName();
					PushMsgEntity pushMsgEntity = createMsg(accountOpenEntity.getUserNumber(), content, MsgTypeEnum.SYS);
					pushMsgService.save(pushMsgEntity);
					//推送
					pushMsg(pushMsgEntity);
				} catch (Throwable e) {
					throw new WbSysException(ErrorCode.PUSH_TASK_EXCEPTION, e);
				}
			}
		});
	}


	private PushMsgEntity createMsg(String userNumber, String content, MsgTypeEnum msgTypeEnum) {
		PushMsgEntity pushMsgEntity = new PushMsgEntity();
		pushMsgEntity.setOperator(Constant.YOP_APP_KEY);
		pushMsgEntity.setTitle(msgTypeEnum.getDisplayName());
		pushMsgEntity.setUserNumbers(userNumber);
		pushMsgEntity.setContent(content);
		pushMsgEntity.setType(msgTypeEnum);
		pushMsgService.save(pushMsgEntity);
		return pushMsgEntity;
	}

	public String getPayContentJson(Payment payment) {
		PaymentDTO paymentDTO = new PaymentDTOConvert().convert2DTO(payment);
		return new Gson().toJson(paymentDTO);
	}

	private void pushMsg(PushMsgEntity pushMsgEntity) {
		String jpushId = jPushService.push2Customers(pushMsgEntity);
		pushMsgEntity.setJpushId(jpushId);
		pushMsgEntity.setPushStatus(PushStatus.SENDED);
		pushMsgEntity.setPushTime(new Date());
		pushMsgEntity.setPushType(PushType.TO_CUSTOMER);
		pushMsgService.updateAfterSend(pushMsgEntity);
	}
}
