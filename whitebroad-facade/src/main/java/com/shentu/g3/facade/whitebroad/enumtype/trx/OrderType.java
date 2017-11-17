/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.facade.whitebroad.enumtype.trx;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名称: OrderType <br>
 * 类描述: 订单结算类型
 *
 * @author: dongxu
 * @since: 17/9/18 下午3:42
 * @version: 1.0.0
 */

public enum OrderType {

    T1("T1", "常规"),
    S0("S0", "秒到");

    private String value;
    private String displayName;
    private static final Map<String, OrderType> VALUE_MAP = new HashMap<String, OrderType>();

    static {
        for (OrderType item : OrderType.values()) {
            VALUE_MAP.put(item.value, item);
        }
    }


    OrderType(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }


    public static OrderType parse(String value) {
        return VALUE_MAP.get(value);
    }

    OrderType(String displayName) {
        this.displayName = displayName;
    }

    public String getValue() {
        return value;
    }
    public String getDisplayName() {
        return displayName;
    }
    public static Map<String, OrderType> getValueMap() {
        return VALUE_MAP;
    }
}