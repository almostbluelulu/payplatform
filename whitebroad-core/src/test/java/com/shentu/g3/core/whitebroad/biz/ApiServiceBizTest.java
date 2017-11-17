/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.core.whitebroad.biz;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shentu.g3.core.whitebroad.BaseTest;
import com.shentu.g3.facade.whitebroad.facade.WbApiInfoFacade;


public class ApiServiceBizTest extends BaseTest {
    @Autowired
    WbApiInfoFacade apiInfoFacade;
    
    @Test
    public void getUrl(){
    	apiInfoFacade.queryAllApi();
    			
    	System.out.println("ok");
    }

}