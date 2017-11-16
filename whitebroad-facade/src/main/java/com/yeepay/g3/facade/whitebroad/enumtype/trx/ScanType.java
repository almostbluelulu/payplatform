/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.facade.whitebroad.enumtype.trx;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ScanType
 * @Description: ScanType 下单方式
 * @author: dongxulu
 * @date: 17/9/18 下午3:51
 * @version: 1.0.0
 */
public enum ScanType {
    DESK_QR("台签码"),
    PAY_QR("收款码"),
    PASSIVE_QR("被扫");

    private String value;
    private String displayName;
    private static final Map<String, ScanType> VALUE_MAP = new HashMap<String, ScanType>();

    static {
        for (ScanType item : ScanType.values()) {
            VALUE_MAP.put(item.value, item);
        }
    }


    ScanType(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }


    public static ScanType parse(String value) {
        return VALUE_MAP.get(value);
    }

    ScanType(String displayName) {
        this.displayName = displayName;
    }

    public String getValue() {
        return value;
    }
    public String getDisplayName() {
        return displayName;
    }
    public static Map<String, ScanType> getValueMap() {
        return VALUE_MAP;
    }
}