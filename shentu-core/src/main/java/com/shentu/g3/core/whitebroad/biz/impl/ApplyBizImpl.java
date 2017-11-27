package com.shentu.g3.core.whitebroad.biz.impl;

import com.shentu.g3.core.whitebroad.biz.AbstractBiz;
import com.shentu.g3.core.whitebroad.biz.ApplyBiz;
import com.shentu.g3.core.whitebroad.entity.AccountOpenEntity;
import com.shentu.g3.core.whitebroad.entity.UserEntity;
import com.shentu.g3.core.whitebroad.task.AsyncPushTask;
import com.shentu.g3.core.whitebroad.parser.impl.ApplyConvert;
import com.shentu.g3.core.whitebroad.util.RemoteFacadeFactory;
import com.shentu.g3.facade.whitebroad.dto.BaseResponse;
import com.shentu.g3.facade.whitebroad.dto.apply.*;
import com.shentu.g3.facade.whitebroad.enumtype.CompanyType;
import com.shentu.g3.facade.whitebroad.enumtype.ExternalSystem;
import com.shentu.g3.facade.whitebroad.enumtype.trx.AccountOpenStatus;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;
import com.shentu.g3.facade.whitebroad.util.PropertyUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 入网相关业务处理
 *
 * @author hongyu.liu
 * @date 2017年9月20日 下午3:04:04
 */
@Component("applyBiz")
public class ApplyBizImpl extends AbstractBiz implements ApplyBiz {

	@Autowired
    AsyncPushTask asyncPushTask;
	
	@SuppressWarnings("unchecked")
	@Override
	public UploadResponseDTO fileUpload(UploadRequestDTO uploadRequestDTO) {
		UploadResponseDTO uploadResponseDTO = new UploadResponseDTO();
		try {
            if (StringUtils.isNotBlank(uploadRequestDTO.getIdcardImg1())) {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("image_base64", uploadRequestDTO.getIdcardImg1());
                Map<String, String> result = (Map<String, String>) RemoteFacadeFactory.getYopService(ExternalSystem.UPLOAD, params);
                if (result != null && "SUCCESS".equals(result.get("status"))) {
                    uploadResponseDTO.setIdcardImg1(result.get("filePath"));
                }
            }
            if (StringUtils.isNotBlank(uploadRequestDTO.getIdcardImg2())) {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("image_base64", uploadRequestDTO.getIdcardImg2());
                Map<String, String> result = (Map<String, String>) RemoteFacadeFactory.getYopService(ExternalSystem.UPLOAD, params);
                if (result != null && "SUCCESS".equals(result.get("status"))) {
                    uploadResponseDTO.setIdcardImg2(result.get("filePath"));
                }
            }
            if (StringUtils.isNotBlank(uploadRequestDTO.getBankcardImg())) {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("image_base64", uploadRequestDTO.getBankcardImg());
                Map<String, String> result = (Map<String, String>) RemoteFacadeFactory.getYopService(ExternalSystem.UPLOAD, params);
                if (result != null && "SUCCESS".equals(result.get("status"))) {
                    uploadResponseDTO.setBankcardImg(result.get("filePath"));
                }
            }
            if (StringUtils.isNotBlank(uploadRequestDTO.getHandidcardImg())) {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("image_base64", uploadRequestDTO.getHandidcardImg());
                Map<String, String> result = (Map<String, String>) RemoteFacadeFactory.getYopService(ExternalSystem.UPLOAD, params);
                if (result != null && "SUCCESS".equals(result.get("status"))) {
                    uploadResponseDTO.setHandidcardImg(result.get("filePath"));
                }
            }
        } catch(Throwable t){
			throw t;
		}
		return uploadResponseDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ApplyResponseDTO apply(ApplyRequestDTO applyRequestDTO) throws Throwable {
		UserEntity userEntity = null;
		AccountOpenEntity accountOpenEntity = null;
		ApplyResponseDTO applyResponseDTO = null;
		ApplyConvert applyConvert = new ApplyConvert();
		try {
			// 1、用户是否存在
			userEntity = userService.findRegisterByUserNo(applyRequestDTO.getUserNumber());
			// 2、组装开户实体
			accountOpenEntity = applyConvert.convert2Entity(applyRequestDTO);
			// 3、开户记录落库
			applyService.insertAccountOpenEntity(accountOpenEntity);
		} catch(Throwable t){
			if(t instanceof WbSysException 
					&& ErrorCode.ACCOUNT_OPEN_EXIST_EXCEPTION.equals(((WbSysException) t).getDefineCode())){
				// 开户失败用户重复开户，更新开户记录
                String customerNo = applyService.getAccountOpenEntityByUserNumber(applyRequestDTO.getUserNumber()).getCustomerNo();
                accountOpenEntity.setCustomerNo(customerNo);
                applyService.updateAccountOpenEntity(accountOpenEntity);
			} else {
				throw t;
			}
		}
		// 4、调用YOP入网接口
		Map<String, Object> params = null;
		Map<String, String> result = null;
        if (CompanyType.SMALL_MICRO.name().equals(applyRequestDTO.getCompanyType())) {
            // 4.1、组装请求YOP入网接口入参
			params = applyConvert.createYopRequestParamsForSmallMicro(accountOpenEntity);
			// 4.2、调用YOP入网接口
			result = (Map<String, String>) RemoteFacadeFactory.getYopService(ExternalSystem.APPLY_SMALL_MICRO, params);
        } else if (CompanyType.INDIVIDUAL.name().equals(applyRequestDTO.getCompanyType())) {
            params = applyConvert.createYopRequestParamsForIndividual(accountOpenEntity);
			result = (Map<String, String>) RemoteFacadeFactory.getYopService(ExternalSystem.APPLY_INDIVIDUAL, params);
        } else if (CompanyType.COMPANY.name().equals(applyRequestDTO.getCompanyType())) {
            params = applyConvert.createYopRequestParamsForCompany(accountOpenEntity);
			result = (Map<String, String>) RemoteFacadeFactory.getYopService(ExternalSystem.APPLY_COMPANY, params);
		}
		if(null != result && "REG00000".equals(result.get("returnCode"))){
	    	// 5、更新开户记录状态为审核中、更新开户表的ypCustomerNo、customerNo回写到user表
	    	applyService.updaAccountOpenEntityAndUserEntity(accountOpenEntity, userEntity, result.get("merchantNo"));
	    	// 6、组装出参
	    	applyResponseDTO = applyConvert.createApplyResponseDTO(accountOpenEntity, result.get("agreementContent"));
	    	// 7、向商户推送开户进度通知
			asyncPushTask.pushOpenMsg2APP(accountOpenEntity.getCustomerNo());
			return applyResponseDTO;
        } else {
            throw new WbSysException(result.get("returnCode"), result.get("returnMsg"));
        }
	}
	
	@Override
	public ApplyResponseDTO queryApply(String userNumber) {
		ApplyResponseDTO applyResponseDTO = null;
		try {
			AccountOpenEntity accountOpenEntity = applyService.getAccountOpenEntityByUserNumber(userNumber);
			applyResponseDTO = new ApplyConvert().createApplyResponseDTO(accountOpenEntity, null);
		} catch(Throwable t){
			if(t instanceof WbSysException){
				throw t;
			}
			throw new WbSysException(ErrorCode.SYSTEM_EXCEPTION, t);
		}
		return applyResponseDTO;
	}

	@Override
	public QueryCodeResponseDTO queryCode(QueryCodeDTO queryCodeDTO) {
		QueryCodeResponseDTO queryCodeResponseDTO = new QueryCodeResponseDTO();
		try {
			String result = null;
			switch(queryCodeDTO.getCodeType()){
				case "PROVINCE":
					result = PropertyUtil.getInstance("province-code").getProperty(queryCodeDTO.getCode());
					queryCodeResponseDTO.setQueryResult(result);
					return queryCodeResponseDTO;
				case "CITY_OR_DISTRICT":
					result = PropertyUtil.getInstance("city-district-code").getProperty(queryCodeDTO.getCode());
					queryCodeResponseDTO.setQueryResult(result);
					return queryCodeResponseDTO;
                case "BRANCH_BANK":
                    result = PropertyUtil.getInstance("branch-bank-code").getProperty(queryCodeDTO.getCode());
                    queryCodeResponseDTO.setQueryResult(result);
					return queryCodeResponseDTO;
                case "BANK":
                    result = PropertyUtil.getInstance("bank-code").getProperty(queryCodeDTO.getCode());
                    queryCodeResponseDTO.setQueryResult(result);
                    return queryCodeResponseDTO;
                case "BANK_LIST":
                    result = PropertyUtil.getInstance("settle-bank").getProperty("settleBank");
                    queryCodeResponseDTO.setQueryResult(result);
                    return queryCodeResponseDTO;
                case "LEVEL1":
					result = PropertyUtil.getInstance("level1-code").getProperty(queryCodeDTO.getCode());
					queryCodeResponseDTO.setQueryResult(result);
					return queryCodeResponseDTO;
				case "LEVEL2":
					result = PropertyUtil.getInstance("level2-code").getProperty(queryCodeDTO.getCode());
					queryCodeResponseDTO.setQueryResult(result);
					return queryCodeResponseDTO;
				default:
					result = null;
					queryCodeResponseDTO.setQueryResult(result);
					return queryCodeResponseDTO;
			}
		} catch(Throwable t){
			throw new WbSysException(ErrorCode.SYSTEM_EXCEPTION, t);
		}
	}
	
	@Override
    public BaseResponse processApplyCallbackFromYop(YOPCallBackDTO params) {
        BaseResponse response = new BaseResponse();
        try {
            if (null != params) {
                String merNetInStatus = params.getMerNetInStatus();
                String requestNo = params.getRequestNo();
                // 待审核
				if("INIT".equals(merNetInStatus)){
					// do nothing
                    return response;
                }
				// 审核中
				if("PROCESSING".equals(merNetInStatus)){
					// do nothing
                    return response;
                }
				// 审核通过
				if("PROCESS_SUCCESS".equals(merNetInStatus)){
					AccountOpenEntity accountOpenEntity = new AccountOpenEntity();
					accountOpenEntity.setCustomerNo(requestNo);
					accountOpenEntity.setAccountOpenStatus(AccountOpenStatus.SUCCESS);
					applyService.updateAccountOpenEntity(accountOpenEntity);
					// 向商户推送开户进度通知
			    	asyncPushTask.pushOpenMsg2APP(requestNo);
                    return response;
                }
				// 审核拒绝
				if("PROCESS_REJECT".equals(merNetInStatus)){
					AccountOpenEntity accountOpenEntity = new AccountOpenEntity();
					accountOpenEntity.setCustomerNo(requestNo);
					accountOpenEntity.setAccountOpenStatus(AccountOpenStatus.REJECT);
					applyService.updateAccountOpenEntity(accountOpenEntity);
					// 向商户推送开户进度通知
					asyncPushTask.pushOpenMsg2APP(requestNo);
                    return response;
                }
				// 审核退回
				if("PROCESS_BACK".equals(merNetInStatus)){
					AccountOpenEntity accountOpenEntity = new AccountOpenEntity();
					accountOpenEntity.setCustomerNo(requestNo);
					accountOpenEntity.setAccountOpenStatus(AccountOpenStatus.RETURN);
					applyService.updateAccountOpenEntity(accountOpenEntity);
					// 向商户推送开户进度通知
					asyncPushTask.pushOpenMsg2APP(requestNo);
                    return response;
                }
			}
			return null;
		} catch(Throwable t){
			throw new WbSysException(ErrorCode.SYSTEM_EXCEPTION);
		}
	}
	
}
