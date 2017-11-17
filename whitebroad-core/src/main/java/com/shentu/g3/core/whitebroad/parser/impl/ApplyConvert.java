package com.shentu.g3.core.whitebroad.parser.impl;

import com.shentu.g3.core.whitebroad.entity.AccountOpenEntity;
import com.shentu.g3.core.whitebroad.parser.DTOConvert;
import com.shentu.g3.core.whitebroad.util.Constant;
import com.shentu.g3.core.whitebroad.util.DateUtils;
import com.shentu.g3.facade.whitebroad.dto.apply.ApplyRequestDTO;
import com.shentu.g3.facade.whitebroad.dto.apply.ApplyResponseDTO;
import com.shentu.g3.facade.whitebroad.enumtype.CompanyType;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;
import com.shentu.g3.facade.whitebroad.util.PropertyUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: ApplyRequestDTO&AccountOpenEntity Convert
 * Author: jiawen.huang
 * Date: 2017/9/19
 * Time: 20:01
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class ApplyConvert implements DTOConvert<ApplyRequestDTO, AccountOpenEntity> {

    private static String BANK_PROVINCE_CODE = "110000";

    private static String BANK_CITY_CODE = "110100";

	/**
	 * ApplyRequestDTO --> AccountOpenEntity
	 * @param applyRequestDTO
	 * @return AccountOpenEntity
	 * @throws
	 */
	@Override
	public AccountOpenEntity convert2Entity(ApplyRequestDTO applyRequestDTO) {
		AccountOpenEntity accountOpenEntity = new AccountOpenEntity();
		try {
			BeanUtils.copyProperties(applyRequestDTO, accountOpenEntity);
			accountOpenEntity.setCompanyType(CompanyType.parse(applyRequestDTO.getCompanyType()));
			if(StringUtils.isNotBlank(applyRequestDTO.getOrgCodeExpiry())){
				accountOpenEntity.setOrgCodeExpiry(DateUtils.parseDate(applyRequestDTO.getOrgCodeExpiry(), "yyyy-MM-dd"));
			}
			if (CompanyType.SMALL_MICRO.equals(CompanyType.parse(applyRequestDTO.getCompanyType()))) {
				accountOpenEntity.setMerFullName(applyRequestDTO.getLegalName());
                accountOpenEntity.setMerShortName(applyRequestDTO.getLegalName());
            }
		} catch(Throwable t){
			throw new WbSysException(ErrorCode.SYSTEM_EXCEPTION);
		}
		return accountOpenEntity;
	}

	@Override
	public ApplyRequestDTO convert2DTO(AccountOpenEntity accountOpenEntity) {
		return null;
	}
	
	/**
	 * 小微用户请求yop入网接口入参
	 * @param accountOpenEntity
	 * @return Map<String,Object>
	 * @throws
	 */
	public Map<String, Object> createYopRequestParamsForSmallMicro(AccountOpenEntity accountOpenEntity){
		Map<String, Object> params = new HashMap<String, Object>();
		// 入网请求号，必填
        params.put("requestNo", accountOpenEntity.getCustomerNo());
        // 接口调用方所在易宝的商户编号，必填
		params.put("parentMerchantNo", Constant.MERCHANT_NUMBER);
		// 法人姓名，必填
		params.put("legalName", accountOpenEntity.getLegalName());
		// 法人身份证号，必填
		params.put("legalIdCard", accountOpenEntity.getLegalIdCard());
		// 商户法人手机，必填
		params.put("merLegalPhone", accountOpenEntity.getLegalPhoneNo());
		// 商户法人邮箱，非必填
		params.put("merLegalEmail", "");
		// 商户一级分类编码，必填，要求传递编码，详见【一二级分类.xlsx】
        params.put("merLevel1No", "109");//小微入网 先传默认
        // 商户二级分类编码，必填，要求传递编码，详见【一二级分类.xlsx】
        params.put("merLevel2No", "109007");//小微入网 先传默认
        // 商户经营地址所在省，必填，要求传递编码，详见【省市区编码.xlsx】
		params.put("merProvince", accountOpenEntity.getMerProvinceCode());
		// 商户经营地址所在市，必填，要求传递编码，详见【省市区编码.xlsx】
		params.put("merCity", accountOpenEntity.getMerCityCode());
		// 商户经营地址所在区，必填，要求传递编码，详见【省市区编码.xlsx】
		params.put("merDistrict", accountOpenEntity.getMerDistrictCode());
		// 商户经营地址所在具体地址，必填
		params.put("merAddress", accountOpenEntity.getMerAddress());
		// 商户经营范围，必填
        params.put("merScope", "批发");
        // 结算银行账号或者银行卡号，必填
		params.put("cardNo", accountOpenEntity.getSettleCardNumber());
		// 开户银行总行编码，必填，要求传递编码，详见【银行编码.xlsx】
		params.put("headBankCode", accountOpenEntity.getHeadBankCode());
		// 开户行支行编码，必填，可通过支行信息接口查询 要求传递编码
        params.put("bankCode", PropertyUtil.getInstance("branch-bank-code").getProperty(accountOpenEntity.getHeadBankCode()));
        // 开户省，必填，要求传递编码，详见【省市区编码.xlsx】
        params.put("bankProvince", BANK_PROVINCE_CODE);
        // 开户市，必填，要求传递编码，详见【省市区编码.xlsx】
        params.put("bankCity", BANK_CITY_CODE);
        // 开通产品，必填
		String productInfo = PropertyUtil.getInstance("apply").getProperty("productInfo");
		params.put("productInfo", productInfo);
		// 资质影印件，必填
		params.put("fileInfo", accountOpenEntity.getFileInfo());
		/* 业务功能，非必填
		 * KEY:AUTO_SETTLEMENT_TYPE 自动结算类型；VALUE:T1(t+1结算)或者 D1(D1结算)；
		 * KEY:SUBACCOUNT_TYPE分账类型；VALUE: SUBACCOUNT_ORDER订单分账
		 * KEY:SUBACCOUNT_IS_OPENED 是否开启被分账，VALUE: yes 
		 *（如果“是否被分账”属性为“是”，则商户“开通产品”字段允许为空。）
		 */
		params.put("businessFunction", "");
		// 商户回调地址，必填，入网审批完成后调用方接收审批结果的地址
		params.put("notifyUrl", Constant.RECEIVE_APPLY_CALLBACK_ADDRESS_FROM_YOP);
		// 授权类型，必填，INTERFACE_AUTHORIZE("接口授权")，SMS_AUTHORIZE("短信授权")，WEB_AUTHORIZE("页面授权");
		String merAuthorizeType = PropertyUtil.getInstance("apply").getProperty("merAuthorizeType");
		params.put("merAuthorizeType", merAuthorizeType);
		return params;
	}
	
	/**
	 * 个体用户请求yop入网接口参数
	 * @param accountOpenEntity
	 * @return Map<String,Object>
	 * @throws
	 */
	public Map<String, Object> createYopRequestParamsForIndividual(AccountOpenEntity accountOpenEntity){
		Map<String, Object> params = new HashMap<String, Object>();
		// 入网请求号，必填
		params.put("requestNo", accountOpenEntity.getUserNumber());
		// 接口调用方所在易宝的商户编号，必填
		params.put("parentMerchantNo", Constant.MERCHANT_NUMBER);
		// 商户全称，必填，必须与营业执照或其他资质证明上的名称一致
		params.put("merFullName", accountOpenEntity.getMerFullName());
		// 商户品牌名称/简称，必填，收银台上显示的收款方名称
		params.put("merShortName", accountOpenEntity.getMerShortName());
		// 营业执照证件号，必填，为所对应的证件类型的证件编号
		params.put("merCertNo", accountOpenEntity.getMerCertNo());
		// 法人姓名，必填
		params.put("legalName", accountOpenEntity.getLegalName());
		// 法人身份证号，必填
		params.put("legalIdCard", accountOpenEntity.getLegalIdCard());
		// 商户法人手机，必填
		params.put("merLegalPhone", accountOpenEntity.getLegalPhoneNo());
		// 商户法人邮箱，非必填
		params.put("merLegalEmail", "");
		// 商户一级分类编码，必填，要求传递编码，详见【一二级分类.xlsx】
		params.put("merLevel1No", accountOpenEntity.getMerLevel1No());
		// 商户二级分类编码，必填，要求传递编码，详见【一二级分类.xlsx】
		params.put("merLevel2No", accountOpenEntity.getMerLevel2No());
		// 商户经营地址所在省，必填，要求传递编码，详见【省市区编码.xlsx】
		params.put("merProvince", accountOpenEntity.getMerProvinceCode());
		// 商户经营地址所在市，必填，要求传递编码，详见【省市区编码.xlsx】
		params.put("merCity", accountOpenEntity.getMerCityCode());
		// 商户经营地址所在区，必填，要求传递编码，详见【省市区编码.xlsx】
		params.put("merDistrict", accountOpenEntity.getMerDistrictCode());
		// 商户经营地址所在具体地址，必填
		params.put("merAddress", accountOpenEntity.getMerAddress());
		// 结算银行账号或者银行卡号，必填
		params.put("cardNo", accountOpenEntity.getSettleCardNumber());
		// 开户银行总行编码，必填，要求传递编码，详见【银行编码.xlsx】
		params.put("headBankCode", accountOpenEntity.getHeadBankCode());
		// 开户行支行编码，必填，可通过支行信息接口查询 要求传递编码
		params.put("bankCode", accountOpenEntity.getBankCode());
		// 开户省，必填，要求传递编码，详见【省市区编码.xlsx】
		params.put("bankProvince", accountOpenEntity.getBankProvinceCode());
		// 开户市，必填，要求传递编码，详见【省市区编码.xlsx】
		params.put("bankCity", accountOpenEntity.getBankCityCode());
		// 开通产品，必填
		String productInfo = PropertyUtil.getInstance("apply").getProperty("productInfo");
		params.put("productInfo", productInfo);
		// 资质影印件，必填
		params.put("fileInfo", accountOpenEntity.getFileInfo());
		/* 业务功能，非必填
		 * KEY:AUTO_SETTLEMENT_TYPE 自动结算类型；VALUE:T1(t+1结算)或者 D1(D1结算)；
		 * KEY:SUBACCOUNT_TYPE分账类型；VALUE: SUBACCOUNT_ORDER订单分账
		 * KEY:SUBACCOUNT_IS_OPENED 是否开启被分账，VALUE: yes 
		 *（如果“是否被分账”属性为“是”，则商户“开通产品”字段允许为空。）
		 */
		params.put("businessFunction", "");
		// 商户回调地址，必填，入网审批完成后调用方接收审批结果的地址
		params.put("notifyUrl", Constant.RECEIVE_APPLY_CALLBACK_ADDRESS_FROM_YOP);
		// 授权类型，必填，INTERFACE_AUTHORIZE("接口授权")，SMS_AUTHORIZE("短信授权")，WEB_AUTHORIZE("页面授权");
		String merAuthorizeType = PropertyUtil.getInstance("apply").getProperty("merAuthorizeType");
		params.put("merAuthorizeType", merAuthorizeType);
		return params;
	}
	
	/**
	 * 企业用户请求yop入网接口参数
	 * @param accountOpenEntity
	 * @return Map<String,Object>
	 * @throws
	 */
	public Map<String, Object> createYopRequestParamsForCompany(AccountOpenEntity accountOpenEntity){
		Map<String, Object> params = new HashMap<String, Object>();
		// 入网请求号，必填
		params.put("requestNo", accountOpenEntity.getUserNumber());
		// 接口调用方所在易宝的商户编号，必填
		params.put("parentMerchantNo", Constant.MERCHANT_NUMBER);
		// 商户全称，必填，必须与营业执照或其他资质证明上的名称一致
		params.put("merFullName", accountOpenEntity.getMerFullName());
		// 商户品牌名称/简称，必填，收银台上显示的收款方名称
		params.put("merShortName", accountOpenEntity.getMerShortName());
		// 证件类型，必填，UNI_CREDIT_CODE=统一社会信用代码证；CORP_CODE ＝营业执照
		params.put("merCertType", accountOpenEntity.getMerCertType());
		// 营业执照证件号，必填，为所对应的证件类型的证件编号
		params.put("merCertNo", accountOpenEntity.getMerCertNo());
		// 法人姓名，必填
		params.put("legalName", accountOpenEntity.getLegalName());
		// 法人身份证号，必填
		params.put("legalIdCard", accountOpenEntity.getLegalIdCard());
		// 商户一级分类编码，必填，要求传递编码，详见【一二级分类.xlsx】
		params.put("merLevel1No", accountOpenEntity.getMerLevel1No());
		// 商户二级分类编码，必填，要求传递编码，详见【一二级分类.xlsx】
		params.put("merLevel2No", accountOpenEntity.getMerLevel2No());
		// 商户经营地址所在省，必填，要求传递编码，详见【省市区编码.xlsx】
		params.put("merProvince", accountOpenEntity.getMerProvinceCode());
		// 商户经营地址所在市，必填，要求传递编码，详见【省市区编码.xlsx】
		params.put("merCity", accountOpenEntity.getMerCityCode());
		// 商户经营地址所在区，必填，要求传递编码，详见【省市区编码.xlsx】
		params.put("merDistrict", accountOpenEntity.getMerDistrictCode());
		// 商户经营地址所在具体地址，必填
		params.put("merAddress", accountOpenEntity.getMerAddress());
		// 商户联系人姓名，必填
		params.put("merContactName", accountOpenEntity.getMerContactName());
		// 商户联系人手机，必填
		params.put("merContactPhone", accountOpenEntity.getMerContactPhone());
		// 商户联系人邮箱，必填
		params.put("merContactEmail", accountOpenEntity.getMerContactEmail());
		// 税务登记证编号，签约类型为“企业”，且证件类型为“营业执照”，则必填
		params.put("taxRegistCert", accountOpenEntity.getTaxRegistCert());
		// 开户许可证编号，必填
		params.put("accountLicense", accountOpenEntity.getAccountLicense());
		// 组织机构代码，签约类型为“企业”，且证件类型为“营业执照”，则必填
		params.put("orgCode", accountOpenEntity.getOrgCode());
		// 组织机构代码证是否长期有效，签约类型为“企业”，且证件类型为“营业执照”，则必填，长期有效=true、非长期有效=false
		params.put("isOrgCodeLong", accountOpenEntity.getIsOrgCodeLong());
		// 组织机构代理证有效期，签约类型为“企业”，且证件类型为“营业执照”，则必填，如果为长期有效则不填；时间格式：YYYY-MM-DD
		params.put("orgCodeExpiry", accountOpenEntity.getOrgCodeExpiry());
		// 结算银行账号或者银行卡号，必填
		params.put("cardNo", accountOpenEntity.getSettleCardNumber());
		// 开户银行总行编码，必填，要求传递编码，详见【银行编码.xlsx】
		params.put("headBankCode", accountOpenEntity.getHeadBankCode());
		// 开户行支行编码，必填，可通过支行信息接口查询 要求传递编码
		params.put("bankCode", accountOpenEntity.getBankCode());
		// 开户省，必填，要求传递编码，详见【省市区编码.xlsx】
		params.put("bankProvince", accountOpenEntity.getBankProvinceCode());
		// 开户市，必填，要求传递编码，详见【省市区编码.xlsx】
		params.put("bankCity", accountOpenEntity.getBankCityCode());
		// 开通产品，必填
		String productInfo = PropertyUtil.getInstance("apply").getProperty("productInfo");
		params.put("productInfo", productInfo);
		// 资质影印件，必填
		params.put("fileInfo", accountOpenEntity.getFileInfo());
		/* 业务功能，非必填
		 * KEY:AUTO_SETTLEMENT_TYPE 自动结算类型；VALUE:T1(t+1结算)或者 D1(D1结算)；
		 * KEY:SUBACCOUNT_TYPE分账类型；VALUE: SUBACCOUNT_ORDER订单分账
		 * KEY:SUBACCOUNT_IS_OPENED 是否开启被分账，VALUE: yes 
		 *（如果“是否被分账”属性为“是”，则商户“开通产品”字段允许为空。）
		 */
		params.put("businessFunction", "");
		// 商户回调地址，必填，入网审批完成后调用方接收审批结果的地址
		params.put("notifyUrl", Constant.RECEIVE_APPLY_CALLBACK_ADDRESS_FROM_YOP);
		// 授权类型，必填，INTERFACE_AUTHORIZE("接口授权")，SMS_AUTHORIZE("短信授权")，WEB_AUTHORIZE("页面授权");
		String merAuthorizeType = PropertyUtil.getInstance("apply").getProperty("merAuthorizeType");
		params.put("merAuthorizeType", merAuthorizeType);
		return params;
	}
	
	/**
	 * AccountOpenEntity --> ApplyResponseDTO
	 * @param accountOpenEntity
	 * @param agreementContent
	 * @return ApplyResponseDTO
	 * @throws
	 */
	public ApplyResponseDTO createApplyResponseDTO(AccountOpenEntity accountOpenEntity, String agreementContent){
		ApplyResponseDTO applyResponseDTO = new ApplyResponseDTO();
		try {
			BeanUtils.copyProperties(accountOpenEntity, applyResponseDTO);
			// yop返回协议内容
			applyResponseDTO.setAgreementContent(agreementContent);
			applyResponseDTO.setAccountOpenStatus(accountOpenEntity.getAccountOpenStatus().getValue());
			applyResponseDTO.setCompanyType(accountOpenEntity.getCompanyType().getValue());
			applyResponseDTO.setOrgCodeExpiry(accountOpenEntity.getOrgCodeExpiry() == null ? null : DateUtils.getShortDateStr(accountOpenEntity.getOrgCodeExpiry()));
		} catch(Throwable t){
			throw new WbSysException(ErrorCode.SYSTEM_EXCEPTION);
		}
		return applyResponseDTO;
	}
	
}
