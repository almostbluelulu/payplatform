/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.core.whitebroad.service;

import com.shentu.g3.core.whitebroad.entity.User2PrivateKey;

import java.util.List;

/**
 * @ClassName: User2PrivateKeyService
 * @Description: User2PrivateKeyService
 * @author: dongxulu
 * @date: 17/9/25 下午8:14
 * @version: 1.0.0
 */
public interface User2PrivateKeyService {

    public void insert(User2PrivateKey pojo);

    public void insertList(List<User2PrivateKey> pojos);

    public void update(User2PrivateKey pojo);

    public  User2PrivateKey getByUserNumber(String userNumber);
}