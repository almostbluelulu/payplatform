package com.shentu.g3.core.whitebroad.service;

import com.shentu.g3.core.whitebroad.entity.PushMsgEntity;
import com.shentu.g3.facade.whitebroad.enumtype.MsgTypeEnum;

import java.util.List;

/**
 * @Description: 本地消息service
 * @Author: jiawen.huang
 * @Date: 16/10/26
 * @Time: 下午2:35
 */
public interface PushMsgService {

	/**
	 * 保存，不发送
	 *
	 * @param entity
	 * @return
	 */
	PushMsgEntity save(PushMsgEntity entity);

	/**
	 * 推送前修改消息
	 *
	 * @param entity
	 */
	void updateBeforeSend(PushMsgEntity entity);

	/**
	 * 推送后更新
	 *
	 * @param entity
	 */
	void updateAfterSend(PushMsgEntity entity);

	/**
	 * 逻辑删除
	 *
	 * @param entity
	 */
	void delete(PushMsgEntity entity);

	/**
	 * 根据唯一消息号查找
	 *
	 * @param messageNo
	 * @return
	 */
	PushMsgEntity findByMessageNo(String messageNo);

	/**
	 * 计数
	 *
	 * @param startDate
	 * @param endDate
	 * @param msgType
	 * @param userNumber
	 * @return
	 */
	int findTotalCount(String startDate, String endDate, MsgTypeEnum msgType, String userNumber);

	/**
	 * 指定行数(分页)查询结算，不会抛出异常
	 *
	 * @param userNumber
	 * @param msgType
	 * @param startDate
	 * @param endDate
	 * @param fromIndex
	 * @param pageSize
	 * @return
	 */
	List<PushMsgEntity> findMsgByDate(String userNumber, MsgTypeEnum msgType, String startDate, String endDate,
									  int fromIndex, int pageSize);
}
