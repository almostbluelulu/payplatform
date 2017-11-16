/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core.whitebroad.entity;
/**
 * @ClassName: User2PrivateKey
 * @Description: User2PrivateKey
 * @author: dongxulu
 * @date: 17/9/25 下午5:09
 * @version: 1.0.0
 */
public class User2PrivateKey extends IdEntity {

    private static final long serialVersionUID = 3816838094790688718L;
    /**
     *用户名
     */
    private String userNumber;
    /**
     *商编
     */
    private String customerNumber;
    /**
     *私钥
     */
    private String privateKey;


    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}