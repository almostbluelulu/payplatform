/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.facade.whitebroad.enumtype.trx;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: TrxType
 * @Description: TrxType
 * @author: dongxulu
 * @date: 17/9/21 下午8:25
 * @version: 1.0.0
 */
public enum  TrxType {

    PURCHASE("PURCHASE","消费"),

    REFUND("REFUND","退款");
    private String value;
    private String displayName;
    private static final Map<String, TrxType> VALUE_MAP = new HashMap<String, TrxType>();

    static {
        for (TrxType item : TrxType.values()) {
            VALUE_MAP.put(item.value, item);
        }
    }


    TrxType(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }


    public static TrxType parse(String value) {
        return VALUE_MAP.get(value);
    }

    TrxType(String displayName) {
        this.displayName = displayName;
    }

    public String getValue() {
        return value;
    }
    public String getDisplayName() {
        return displayName;
    }
    public static Map<String, TrxType> getValueMap() {
        return VALUE_MAP;
    }
}