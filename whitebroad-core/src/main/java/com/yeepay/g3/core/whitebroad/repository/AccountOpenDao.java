package com.yeepay.g3.core.whitebroad.repository;

import com.yeepay.g3.core.whitebroad.entity.AccountOpenEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 开户实体dao
 *
 * @author hongyu.liu
 * @date 2017年9月18日 下午4:28:48
 */
@Repository("accountOpenDao")
public interface AccountOpenDao {
	
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
	int updateAccountOpenEntity(AccountOpenEntity accountOpenEntity);
	
	/**
	 * 根据用户编号获取账户实体
	 * @param userNumber
	 * @return AccountOpenEntity
	 * @throws
	 */
	AccountOpenEntity getAccountOpenEntityByUserNumber(@Param( value = "userNumber" )String userNumber);

	/**
	 * 根据商户编号获取账户实体
	 *
	 * @param customerNo
	 * @return AccountOpenEntity
	 * @throws
	 */
	AccountOpenEntity getAccountOpenEntityByCustomerNo(@Param(value = "customerNo") String customerNo);

	/**
	 * 获取数据库序列(11位）
	 * @return Long
	 * @throws
	 */
	Long getSequence();
	
}
