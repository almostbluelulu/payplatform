/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core.whitebroad.biz;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.yeepay.g3.core.whitebroad.BaseTest;
import com.yeepay.g3.core.whitebroad.service.WbUserTokenService;


public class UserTokenServiceTest extends BaseTest {
    
	@Autowired
    WbUserTokenService userTokenService;
    
    @Test
    @Rollback(false)
    public void insert(){
    	userTokenService.generateTokenByUserNo("1007", "IDMFOWEF-DFDFDSFF-DFDFS");
    	
    }

    @Test
    @Rollback(false)
    public void token(){
    	userTokenService.refreshToken("9938ff29cfd28b6586e0bc6a44cdb4f7");
    	
    }
    
    @Test
    @Rollback(false)
    public void revokeToken(){
    	userTokenService.revokeToken("4878b2c399d8b662f3c3b1b4e895713e");
    	
    }

}