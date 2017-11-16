package com.yeepay.g3.facade.whitebroad.enumtype.trx;



import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName: OrderStatus
 * @Description: OrderStatus
 * @author: dongxulu
 * @date: 17/9/18 下午4:18
 * @version: 1.0.0
 */
public enum OrderStatus {

    INIT("INIT","未支付"),
    PROCESS("PROCESS","支付中"),
    SUCCESS("SUCCESS","已支付"),
    SETTELED("SETTELED","已结算"),
    FAIL("FAIL","支付失败");

    private static final Map<String, OrderStatus> VALUE_MAP = new HashMap<String, OrderStatus>();
    private String value;
    private String displayName;

    static {
        for (OrderStatus item : OrderStatus.values()) {
            VALUE_MAP.put(item.value, item);
        }
    }


    OrderStatus(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }


    public static OrderStatus parse(String value) {
        return VALUE_MAP.get(value);
    }

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getValue() {
        return value;
    }
    public String getDisplayName() {
        return displayName;
    }
    public static Map<String, OrderStatus> getValueMap() {
        return VALUE_MAP;
    }

}
