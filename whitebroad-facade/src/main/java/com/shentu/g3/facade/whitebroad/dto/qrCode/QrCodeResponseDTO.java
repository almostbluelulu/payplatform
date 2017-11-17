/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.facade.whitebroad.dto.qrCode;

import com.shentu.g3.facade.whitebroad.dto.BaseResponse;

import java.util.List;

/**
 * @ClassName: QrCodeResponseDTO
 * @Description: QrCodeResponseDTO
 * @author: dongxulu
 * @date: 17/9/23 下午2:45
 * @version: 1.0.0
 */
public class QrCodeResponseDTO extends BaseResponse {

    private static final long serialVersionUID = -4233874190023818470L;
    /**
     * 商户名称
     */
    private String customerNumber;
    /**
     * 商户名称
     */
    private String fullname;
    /**
     * 商户简称
     */
    private String shortName;
    /**
     * 二维码信息
     */
    private List<QrCodeInfoDTO> qrCodeInfoDTOList;
    /**
     * 用户编号
     */
    private String userNumber;
    /**
     * 台签牌id
     */
    private String qrId;

    public String getQrId() {
        return qrId;
    }

    public void setQrId(String qrId) {
        this.qrId = qrId;
    }

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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<QrCodeInfoDTO> getQrCodeInfoDTOList() {
        return qrCodeInfoDTOList;
    }

    public void setQrCodeInfoDTOList(List<QrCodeInfoDTO> qrCodeInfoDTOList) {
        this.qrCodeInfoDTOList = qrCodeInfoDTOList;
    }
}