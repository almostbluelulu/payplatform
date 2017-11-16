package com.yeepay.g3.facade.whitebroad.dto.apply;

import com.yeepay.g3.facade.whitebroad.dto.BaseRequest;

/**
 * Description: yop回调
 * Author: wei.li
 * Date: 17/10/18
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class YOPCallBackDTO extends BaseRequest {

    private static final long serialVersionUID = 3282807724085199183L;

    /**
     * 商户名
     */
    private String merFullName;

    /**
     * 商户编号
     */
    private String merNo;

    /**
     * 商户状态
     */
    private String merNetInStatus;

    /**
     * 代理商编
     */
    private String agentNo;

    /**
     * externalId
     */
    private String externalId;

    public String getMerFullName() {
        return merFullName;
    }

    public void setMerFullName(String merFullName) {
        this.merFullName = merFullName;
    }

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }

    public String getMerNetInStatus() {
        return merNetInStatus;
    }

    public void setMerNetInStatus(String merNetInStatus) {
        this.merNetInStatus = merNetInStatus;
    }

    public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
