package com.shentu.g3.core.whitebroad.repository;

import com.shentu.g3.core.whitebroad.entity.SmsCodeEntity;
import com.shentu.g3.facade.whitebroad.enumtype.SmsTypeEnum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Description: dao
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 14:04
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Repository
public interface SmsCodeDao {

	void save(SmsCodeEntity smsCodeEntity);

	int setUnAvaliable(Long id);

	SmsCodeEntity findOne(@Param("phoneNumber") String phoneNo, @Param("smsType") SmsTypeEnum smsType);
}
