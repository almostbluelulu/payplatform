package com.shentu.g3.core.whitebroad.service.impl;

import com.shentu.g3.core.whitebroad.service.UserService;
import com.shentu.g3.core.whitebroad.entity.AccountOpenEntity;
import com.shentu.g3.core.whitebroad.entity.UserEntity;
import com.shentu.g3.core.whitebroad.repository.AccountOpenDao;
import com.shentu.g3.core.whitebroad.service.ApplyService;
import com.shentu.g3.core.whitebroad.util.BizUidUtil;
import com.shentu.g3.facade.whitebroad.enumtype.BizPrefixEnum;
import com.shentu.g3.facade.whitebroad.enumtype.trx.AccountOpenStatus;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 入网service实现
 *
 * @author hongyu.liu
 * @date 2017年9月18日 下午3:57:45
 */
@Service("applyService")
public class ApplyServiceImpl implements ApplyService {

	@Autowired
	private AccountOpenDao accountOpenDao;
	@Autowired
	private UserService userService;
	
	public void insertAccountOpenEntity(AccountOpenEntity accountOpenEntity) {
		try {
			// generate customerNo and set
			String customerNo = BizUidUtil.generateBizUID(BizPrefixEnum.CS_PR.getValue(), accountOpenDao.getSequence());
			accountOpenEntity.setCustomerNo(customerNo);
			accountOpenEntity.setAccountOpenStatus(AccountOpenStatus.INIT);
			accountOpenDao.insertAccountOpenEntity(accountOpenEntity);
		} catch(Throwable t){
			// 唯一索引冲突
			if(t instanceof DuplicateKeyException){
				throw new WbSysException(ErrorCode.ACCOUNT_OPEN_EXIST_EXCEPTION, t);
			}
			throw new WbSysException(ErrorCode.SYSTEM_EXCEPTION, t);
		}
	}

	public void updateAccountOpenEntity(AccountOpenEntity accountOpenEntity) {
		int result = accountOpenDao.updateAccountOpenEntity(accountOpenEntity);
		if(result != 1) {
            throw new WbSysException(ErrorCode.UPDATE_FAILED);
        }
	}

	public AccountOpenEntity getAccountOpenEntityByUserNumber(String userNumber) {
		AccountOpenEntity accountOpenEntity = accountOpenDao.getAccountOpenEntityByUserNumber(userNumber);
		if(null == accountOpenEntity){
			throw new WbSysException(ErrorCode.ACCOUNT_NOT_EXIST);
		}
		return accountOpenEntity;
	}

	@Override
	public AccountOpenEntity getAccountOpenEntityByCustomerNo(String customerNo) {
		AccountOpenEntity accountOpenEntity = accountOpenDao.getAccountOpenEntityByCustomerNo(customerNo);
		if (null == accountOpenEntity) {
			throw new WbSysException(ErrorCode.ACCOUNT_NOT_EXIST);
		}
		return accountOpenEntity;
	}

	@Override
	public AccountOpenEntity getPayableByUserNumber(String userNumber) {
		AccountOpenEntity accountOpenEntity = accountOpenDao.getAccountOpenEntityByUserNumber(userNumber);
		if (null == accountOpenEntity) {
			throw new WbSysException(ErrorCode.ACCOUNT_NOT_EXIST);
		}
		if (!AccountOpenStatus.SUCCESS.equals(accountOpenEntity.getAccountOpenStatus())) {
			throw new WbSysException(ErrorCode.ACCOUNT_STATUS_DENY);
		}
		return accountOpenEntity;
	}

	@Override
	public AccountOpenEntity getPayableByCustomerNo(String customerNo) {
		AccountOpenEntity accountOpenEntity = accountOpenDao.getAccountOpenEntityByCustomerNo(customerNo);
		if (null == accountOpenEntity) {
			throw new WbSysException(ErrorCode.ACCOUNT_NOT_EXIST);
		}
		if (!accountOpenEntity.getAccountOpenStatus().equals(AccountOpenStatus.SUCCESS)) {
			throw new WbSysException(ErrorCode.ACCOUNT_STATUS_DENY);
		}
		return accountOpenEntity;
	}

	@Transactional(rollbackFor = {Exception.class, Throwable.class}, propagation = Propagation.REQUIRED)
	@Override
	public void updaAccountOpenEntityAndUserEntity(AccountOpenEntity accountOpenEntity, UserEntity userEntity, String merchantNo) {
		accountOpenEntity.setAccountOpenStatus(AccountOpenStatus.AUDITING);
    	accountOpenEntity.setYpCustomerNumber(merchantNo);
    	updateAccountOpenEntity(accountOpenEntity);
    	
    	userEntity.setCustomerNumber(accountOpenEntity.getCustomerNo());
    	userService.update(userEntity);
	}
}
