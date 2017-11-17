package com.shentu.g3.core.whitebroad.service.impl;

import com.shentu.g3.core.whitebroad.repository.PushMsgDao;
import com.shentu.g3.core.whitebroad.service.PushMsgService;
import com.shentu.g3.core.whitebroad.entity.PushMsgEntity;
import com.shentu.g3.core.whitebroad.util.BizUidUtil;
import com.shentu.g3.facade.whitebroad.enumtype.BizPrefixEnum;
import com.shentu.g3.facade.whitebroad.enumtype.MsgTypeEnum;
import com.shentu.g3.facade.whitebroad.enumtype.PushStatus;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PushMsgServiceImpl implements PushMsgService {

	@Autowired
	private PushMsgDao pushMsgDao;

	@Override
	public PushMsgEntity save(PushMsgEntity entity) {
		try {
			String messageNo = BizUidUtil.generateBizUID(BizPrefixEnum.MS.getValue(), pushMsgDao.nextSequence());//生成编号
			entity.setPushStatus(PushStatus.NOT_SEND);
			entity.setMessageNo(messageNo);//可以不判blank，这是唯一索引
			pushMsgDao.save(entity);
			return entity;
		} catch (DuplicateKeyException e) {
			throw new WbSysException(ErrorCode.MSG_CREATE_REPEAT, e);
		}
	}

	@Override
	public void updateBeforeSend(PushMsgEntity entity) {
		Integer num = pushMsgDao.updateBeforeSend(entity);
		if (0 == num) {
			throw new WbSysException(ErrorCode.MSG_UPDATE_STATUS_ERROR);
		}
	}

	@Override
	public void updateAfterSend(PushMsgEntity entity) {
		entity.setPushStatus(PushStatus.SENDED);
		Integer num = pushMsgDao.updateAfterSend(entity);
		if (0 == num) {
			throw new WbSysException(ErrorCode.MSG_UPDATE_STATUS_ERROR);
		}
	}

	@Override
	public void delete(PushMsgEntity entity) {
		Integer num = pushMsgDao.delete(entity);
		if (0 == num) {
			throw new WbSysException(ErrorCode.MSG_UPDATE_STATUS_ERROR);
		}
	}

	@Override
	public PushMsgEntity findByMessageNo(String messageNo) {
		PushMsgEntity msgEntity = pushMsgDao.findByMessageNo(messageNo);
		if (null == msgEntity) {
			throw new WbSysException(ErrorCode.MSG_NOT_EXIST);
		}
		return msgEntity;
	}

	@Override
	public int findTotalCount(String startDate, String endDate, MsgTypeEnum msgType, String userNumber) {
		Integer count = pushMsgDao.findTotalCount(startDate, endDate,
				null == msgType ? null : msgType.getValue(), userNumber);
		return count == null ? 0 : count.intValue();
	}

	@Override
	public List<PushMsgEntity> findMsgByDate(String userNumber, MsgTypeEnum msgType, String startDate, String endDate, int fromIndex, int pageSize) {
		return pushMsgDao.findMsgByDate(userNumber, fromIndex, pageSize,
				msgType == null ? null : msgType.getValue(), startDate, endDate);
	}

}
