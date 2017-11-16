package com.yeepay.g3.facade.whitebroad.dto.apply;

import com.yeepay.g3.facade.whitebroad.dto.BaseResponse;

/**
 * 入网出参
 *
 * @author hongyu.liu
 * @date 2017年9月26日 下午4:43:50
 */
public class ApplyResponseDTO extends BaseResponse {

	private static final long serialVersionUID = -7272723333628339385L;
	
	/**
	 * 用户编号
	 */
	private String userNumber;
	
	/**
	 * 开户状态
	 */
	private String accountOpenStatus;

    /**
     * 商户编号
     */
    private String customerNo;

    /**
     * 商户类型
	 */
	private String companyType;
	
	/**
	 * 法人姓名
	 */
	private String legalName;
	
	/**
	 * 法人手机号
	 */
	private String legalPhoneNo;
	
	/**
	 * 法人身份证号
	 */
	private String legalIdCard;
	
	/**
	 * 商户全称，必须与营业执照或其他资质证明上的名称一致
	 */
	private String merFullName;
	
	/**
	 * 商户品牌名称/简称，收银台上显示的收款方名称
	 */
	private String merShortName;
	
	/**
	 * 证件类型，UNI_CREDIT_CODE=统一社会信用代码证；CORP_CODE ＝营业执照
	 */
	private String merCertType;
	
	/**
	 * 营业执照证件号，为所对应的证件类型的证件编号
	 */
	private String merCertNo;
	
	/**
	 * 商户一级分类编码
	 */
	private String merLevel1No;
	
	/**
	 * 商户二级分类编码
	 */
	private String merLevel2No;
	
	/**
	 * 商户经营地址所在省
	 */
	private String merProvinceCode;

    /**
     * 商户经营地址所在省名称
     */
    private String merProvinceName;

    /**
	 * 商户经营地址所在市
	 */
	private String merCityCode;

    /**
     * 商户经营地址所在市名称
     */
    private String merCityName;

    /**
	 * 商户经营地址所在区
	 */
	private String merDistrictCode;

    /**
     * 商户经营地址所在区名称
     */
    private String merDistrictName;

    /**
	 * 商户经营地址所在具体地址
	 */
	private String merAddress;
	
	/**
	 * 商户联系人姓名
	 */
	private String merContactName;
	
	/**
	 * 商户联系人手机
	 */
	private String merContactPhone;
	
	/**
	 * 商户联系人邮箱
	 */
	private String merContactEmail;
	
	/**
	 * 税务登记证编号，签约类型为“企业”，且证件类型为“营业执照”，则必填
	 */
	private String taxRegistCert;
	
	/**
	 * 开户许可证编号
	 */
	private String accountLicense;
	
	/**
	 * 组织机构代码，签约类型为“企业”，且证件类型为“营业执照”，则必填
	 */
	private String orgCode;
	
	/**
	 * 组织机构代码证是否长期有效，签约类型为“企业”，且证件类型为“营业执照”，则必填，长期有效=true、非长期有效=false
	 */
	private String isOrgCodeLong;
	
	/**
	 * 组织机构代理证有效期，签约类型为“企业”，且证件类型为“营业执照”，则必填，如果为长期有效则不填；时间格式：YYYY-MM-DD
	 */
	private String orgCodeExpiry;
	
	/**
	 * 商户经营范围
	 */
	private String merScope;
	
	/**
	 * 结算银行账号或者银行卡号
	 */
	private String settleCardNumber;
	
	/**
	 * 开户行总行编码
	 */
	private String headBankCode;

    /**
     * 开户行总行名称
     */
    private String headBankName;

    /**
	 * 开户行支行编码
	 */
	private String bankCode;
	
	/**
	 * 开户省编码
	 */
	private String bankProvinceCode;
	
	/**
	 * 开户市编码
	 */
	private String bankCityCode;
	
	/**
	 * 资质影印件
	 */
	private String fileInfo;
	
	/**
	 * 坐标
	 */
	private String openLbs;
	
	/**
	 * 易宝协议内容
	 */
	private String agreementContent;

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getAccountOpenStatus() {
        return accountOpenStatus;
    }

    public void setAccountOpenStatus(String accountOpenStatus) {
        this.accountOpenStatus = accountOpenStatus;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getLegalPhoneNo() {
        return legalPhoneNo;
    }

    public void setLegalPhoneNo(String legalPhoneNo) {
        this.legalPhoneNo = legalPhoneNo;
    }

    public String getLegalIdCard() {
        return legalIdCard;
    }

    public void setLegalIdCard(String legalIdCard) {
        this.legalIdCard = legalIdCard;
    }

    public String getMerFullName() {
        return merFullName;
    }

    public void setMerFullName(String merFullName) {
        this.merFullName = merFullName;
    }

    public String getMerShortName() {
        return merShortName;
    }

    public void setMerShortName(String merShortName) {
        this.merShortName = merShortName;
    }

    public String getMerCertType() {
        return merCertType;
    }

    public void setMerCertType(String merCertType) {
        this.merCertType = merCertType;
    }

    public String getMerCertNo() {
        return merCertNo;
    }

    public void setMerCertNo(String merCertNo) {
        this.merCertNo = merCertNo;
    }

    public String getMerLevel1No() {
        return merLevel1No;
    }

    public void setMerLevel1No(String merLevel1No) {
        this.merLevel1No = merLevel1No;
    }

    public String getMerLevel2No() {
        return merLevel2No;
    }

    public void setMerLevel2No(String merLevel2No) {
        this.merLevel2No = merLevel2No;
    }

    public String getMerProvinceCode() {
        return merProvinceCode;
    }

    public void setMerProvinceCode(String merProvinceCode) {
        this.merProvinceCode = merProvinceCode;
    }

    public String getMerProvinceName() {
        return merProvinceName;
    }

    public void setMerProvinceName(String merProvinceName) {
        this.merProvinceName = merProvinceName;
    }

    public String getMerCityCode() {
        return merCityCode;
    }

    public void setMerCityCode(String merCityCode) {
        this.merCityCode = merCityCode;
    }

    public String getMerCityName() {
        return merCityName;
    }

    public void setMerCityName(String merCityName) {
        this.merCityName = merCityName;
    }

    public String getMerDistrictCode() {
        return merDistrictCode;
    }

    public void setMerDistrictCode(String merDistrictCode) {
        this.merDistrictCode = merDistrictCode;
    }

    public String getMerDistrictName() {
        return merDistrictName;
    }

    public void setMerDistrictName(String merDistrictName) {
        this.merDistrictName = merDistrictName;
    }

    public String getMerAddress() {
        return merAddress;
    }

    public void setMerAddress(String merAddress) {
        this.merAddress = merAddress;
    }

    public String getMerContactName() {
        return merContactName;
    }

    public void setMerContactName(String merContactName) {
        this.merContactName = merContactName;
    }

    public String getMerContactPhone() {
        return merContactPhone;
    }

    public void setMerContactPhone(String merContactPhone) {
        this.merContactPhone = merContactPhone;
    }

    public String getMerContactEmail() {
        return merContactEmail;
    }

    public void setMerContactEmail(String merContactEmail) {
        this.merContactEmail = merContactEmail;
    }

    public String getTaxRegistCert() {
        return taxRegistCert;
    }

    public void setTaxRegistCert(String taxRegistCert) {
        this.taxRegistCert = taxRegistCert;
    }

    public String getAccountLicense() {
        return accountLicense;
    }

    public void setAccountLicense(String accountLicense) {
        this.accountLicense = accountLicense;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getIsOrgCodeLong() {
        return isOrgCodeLong;
    }

    public void setIsOrgCodeLong(String isOrgCodeLong) {
        this.isOrgCodeLong = isOrgCodeLong;
    }

    public String getOrgCodeExpiry() {
        return orgCodeExpiry;
    }

    public void setOrgCodeExpiry(String orgCodeExpiry) {
        this.orgCodeExpiry = orgCodeExpiry;
    }

    public String getMerScope() {
        return merScope;
    }

    public void setMerScope(String merScope) {
        this.merScope = merScope;
    }

    public String getSettleCardNumber() {
        return settleCardNumber;
    }

    public void setSettleCardNumber(String settleCardNumber) {
        this.settleCardNumber = settleCardNumber;
    }

    public String getHeadBankCode() {
        return headBankCode;
    }

    public void setHeadBankCode(String headBankCode) {
        this.headBankCode = headBankCode;
    }

    public String getHeadBankName() {
        return headBankName;
    }

    public void setHeadBankName(String headBankName) {
        this.headBankName = headBankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankProvinceCode() {
        return bankProvinceCode;
    }

    public void setBankProvinceCode(String bankProvinceCode) {
        this.bankProvinceCode = bankProvinceCode;
    }

    public String getBankCityCode() {
        return bankCityCode;
    }

    public void setBankCityCode(String bankCityCode) {
        this.bankCityCode = bankCityCode;
    }

    public String getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(String fileInfo) {
        this.fileInfo = fileInfo;
    }

    public String getOpenLbs() {
        return openLbs;
    }

    public void setOpenLbs(String openLbs) {
        this.openLbs = openLbs;
    }

    public String getAgreementContent() {
        return agreementContent;
    }

    public void setAgreementContent(String agreementContent) {
        this.agreementContent = agreementContent;
    }
}
