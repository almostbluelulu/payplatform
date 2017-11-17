package com.shentu.g3.core.whitebroad.dao;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.shentu.g3.core.whitebroad.BaseTest;
import com.shentu.g3.core.whitebroad.entity.AccountOpenEntity;
import com.shentu.g3.core.whitebroad.repository.AccountOpenDao;
import com.shentu.g3.facade.whitebroad.enumtype.CompanyType;
import com.shentu.g3.facade.whitebroad.enumtype.trx.AccountOpenStatus;

public class AccountOpenDaoTest extends BaseTest {

	@Autowired
	AccountOpenDao accountOpenDao;
	
	@Rollback(false)
	@Test
	public void testInsertAccountOpenEntity(){
		try {
			AccountOpenEntity accountOpenEntity = new AccountOpenEntity();
			accountOpenEntity.setUserNumber("123456");
			accountOpenEntity.setCustomerNo("123456");
			accountOpenEntity.setAccountOpenStatus(AccountOpenStatus.INIT);
			accountOpenEntity.setCompanyType(CompanyType.SMALL_MICRO);
			
			accountOpenDao.insertAccountOpenEntity(accountOpenEntity);
		} catch(Throwable t){
			t.printStackTrace();
		}
	}
	
	@Test
	public void testGetAccountOpenEntityByUserNumber(){
		try {
			String userNumber = "123456";
			AccountOpenEntity accountOpenEntity = accountOpenDao.getAccountOpenEntityByUserNumber(userNumber);
			System.out.println("开户实体查询结果：" + ToStringBuilder.reflectionToString(accountOpenEntity));
		} catch(Throwable t){
			t.printStackTrace();
		}
	}
	
	@Rollback(false)
	@Test
	public void testUpdateAccountOpenEntity(){
		try {
			AccountOpenEntity accountOpenEntity = new AccountOpenEntity();
			accountOpenEntity.setUserNumber("123456");
			accountOpenEntity.setCustomerNo("654321");
			
			int result = accountOpenDao.updateAccountOpenEntity(accountOpenEntity);
			System.out.println("更新结果：" + result);
		} catch(Throwable t){
			t.printStackTrace();
		}
	}
	
	@Rollback(false)
	@Test
	public void testGetSequence(){
		try {
			Long sequence = accountOpenDao.getSequence();
			System.out.println("开户表序列:" + sequence);
		} catch(Throwable t){
			t.printStackTrace();
		}
	}
}
