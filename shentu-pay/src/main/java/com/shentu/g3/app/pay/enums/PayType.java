/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.app.pay.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: PayType
 * @Description: PayType
 * @author: dongxulu
 * @date: 17/9/18 下午4:22
 * @version: 1.0.0
 */
public enum  PayType {

    NCPAY("NCPAY"),
    ALIPAY("支付宝"),
    WECHAT("微信"),
    CFL("分期支付"),
    NET("网银支付"),
    JD("京东钱包"),
    SCAN_CODE("扫码"),
    OPEN_UPOP("银联");

    private String value;
    private String displayName;
    private static final Map<String, PayType> VALUE_MAP = new HashMap<String, PayType>();

    static {
        for (PayType item : PayType.values()) {
            VALUE_MAP.put(item.value, item);
        }
    }


    PayType(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }


    public static PayType parse(String value) {
        return VALUE_MAP.get(value);
    }

    PayType(String displayName) {
        this.displayName = displayName;
    }

    public String getValue() {
        return value;
    }
    public String getDisplayName() {
        return displayName;
    }
    public static Map<String, PayType> getValueMap() {
        return VALUE_MAP;
    }
}