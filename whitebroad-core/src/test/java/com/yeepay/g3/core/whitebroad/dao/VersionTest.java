package com.yeepay.g3.core.whitebroad.dao;

import com.yeepay.g3.core.whitebroad.BaseTest;
import com.yeepay.g3.core.whitebroad.entity.AppVersionEntity;
import com.yeepay.g3.core.whitebroad.repository.AppVersionDao;
import com.yeepay.g3.core.whitebroad.util.BizUidUtil;
import com.yeepay.g3.facade.whitebroad.enumtype.AppRoleEnum;
import com.yeepay.g3.facade.whitebroad.enumtype.BizPrefixEnum;
import com.yeepay.g3.facade.whitebroad.enumtype.VersionPlatform;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * Description: VersionTest
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 18:09
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class VersionTest extends BaseTest {

	@Autowired
	private AppVersionDao appVersionDao;

	@Rollback(false)
	@Test
	public void test() {
		AppVersionEntity versionEntity = new AppVersionEntity();
		String id = BizUidUtil.generateBizUID(BizPrefixEnum.AP.getValue(), appVersionDao.nextSequence());
		versionEntity.setId(id);
		versionEntity.setOperator("jiawen.huang");
		versionEntity.setDescription("测试1");
		versionEntity.setFileUrl("http://www.baidu.com");
		versionEntity.setForceUpdate(false);
		// unique index
		versionEntity.setRoleType(AppRoleEnum.WB_DEMO);
		versionEntity.setPlatform(VersionPlatform.ANDROID);
		versionEntity.setVersionCode("1.0.1");
		// unique index
		int num = appVersionDao.save(versionEntity);
		Assert.assertEquals(num, 1);
	}
}
