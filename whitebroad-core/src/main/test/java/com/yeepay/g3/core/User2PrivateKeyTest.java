/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core;
/**
 * 类名称: User2PrivateKeyTest <br>
 * 类描述: <br>
 *
 * @author: xxxx.xxx
 * @since: 17/9/25 下午8:44
 * @version: 1.0.0
 */

import com.google.gson.Gson;
import com.shentu.g3.core.whitebroad.BaseTest;
import com.shentu.g3.core.whitebroad.entity.User2PrivateKey;
import com.shentu.g3.core.whitebroad.service.User2PrivateKeyService;
import com.shentu.g3.core.whitebroad.util.Constant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * Created by dongxulu on 17/9/25.
 */
public class User2PrivateKeyTest extends BaseTest {
    @Autowired
    User2PrivateKeyService user2PrivateKeyService;
    @Rollback(false)
    @Test
    public void insert(){
        User2PrivateKey user2PrivateKey = new User2PrivateKey();
        user2PrivateKey.setCustomerNumber("10040011560");
        user2PrivateKey.setUserNumber("311234567899");
        user2PrivateKey.setPrivateKey(Constant.YOP_APP_RSA_SECRET);
        user2PrivateKeyService.insert(user2PrivateKey);
    }

    @Test
    public void selectByUserNumber(){
        User2PrivateKey user2PrivateKey = user2PrivateKeyService.getByUserNumber("311234567899");
        System.out.println("@@@@@@"+new Gson().toJson(user2PrivateKey));
    }
}