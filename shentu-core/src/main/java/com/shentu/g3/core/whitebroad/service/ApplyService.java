package com.shentu.g3.core.whitebroad.service;

import com.shentu.g3.core.whitebroad.entity.AccountOpenEntity;
import com.shentu.g3.core.whitebroad.entity.UserEntity;

/**
 * 入网service
 *
 * @author hongyu.liu
 * @date 2017年9月18日 下午3:28:19
 */
public interface ApplyService {

	/**
	 * 添加账户
	 * @param accountOpenEntity
	 * @return void
	 * @throws
	 */
	void insertAccountOpenEntity(AccountOpenEntity accountOpenEntity);
	
	/**
	 * 更新账户
	 * @param accountOpenEntity
	 * @return void
	 * @throws
	 */
	void updateAccountOpenEntity(AccountOpenEntity accountOpenEntity);
	
	/**
	 * 根据用户编号获取账户实体
	 * @param userNumber
	 * @return AccountOpenEntity
	 * @throws
	 */
	AccountOpenEntity getAccountOpenEntityByUserNumber(String userNumber);

    /**
     * 根据商户编号获取账户实体
     *
     * @param customerNo
     * @return AccountOpenEntity
     * @throws
     */
    AccountOpenEntity getAccountOpenEntityByCustomerNo(String customerNo);

	/**
	 * 根据用户编号获取开户成功的账户实体
	 *
	 * @param userNumber
	 * @return
	 */
	AccountOpenEntity getPayableByUserNumber(String userNumber);

	/**
	 * 根据商户编号获取开户成功的账户实体
	 *
	 * @param customerNo
	 * @return
	 */
	AccountOpenEntity getPayableByCustomerNo(String customerNo);
	
	/**
	 * 更新开户记录状态为审核中、更新开户表的ypCustomerNo、customerNo回写到user表
	 * @param accountOpenEntity
	 * @param userEntity
	 * @param merchantNo
	 * @return void
	 * @throws
	 */
	void updaAccountOpenEntityAndUserEntity(AccountOpenEntity accountOpenEntity, UserEntity userEntity, String merchantNo);
}
