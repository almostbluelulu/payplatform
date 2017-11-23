/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.app.pay.dto;

import com.yeepay.g3.facade.whitebroad.dto.BaseResponse;

/**
 * @ClassName: OprPayCallBackResponseParam
 * @Description: OprPayCallBackResponseParam 订单处理器支付,清算回调响应参数
 * @author: dongxulu
 * @date: 17/9/29 下午5:07
 * @version: 1.0.0
 */
public class OprCallBackResponseParam extends BaseResponse {


    private static final long serialVersionUID = -3590329154589600040L;
    /**
     * 处理状态 包含支付与清算  成功回写SUCCESS
     */
    private String manageStatus="SUCCESS";

    public String getManageStatus() {
        return manageStatus;
    }

    public void setManageStatus(String manageStatus) {
        this.manageStatus = manageStatus;
    }
}