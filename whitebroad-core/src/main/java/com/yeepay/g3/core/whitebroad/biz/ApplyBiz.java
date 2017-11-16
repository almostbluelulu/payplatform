package com.yeepay.g3.core.whitebroad.biz;

import com.yeepay.g3.facade.whitebroad.dto.BaseResponse;
import com.yeepay.g3.facade.whitebroad.dto.apply.*;

/**
 * 入网biz
 *
 * @author hongyu.liu
 * @date 2017年9月18日 下午3:30:01
 */
public interface ApplyBiz {

	/**
	 * 图片上传
	 * @param uploadRequestDTO
	 * @return UploadResponseDTO
	 * @throws
	 */
	UploadResponseDTO fileUpload(UploadRequestDTO uploadRequestDTO);
	
	/**
	 * 入网
	 *
	 * @author hongyu.liu
	 * @throws Throwable 
	 * @date 2017年9月18日 下午3:52:10
	 */
	ApplyResponseDTO apply(ApplyRequestDTO applyRequestDTO) throws Throwable;
	
	/**
	 * 入网查询
	 * @param userNumber
	 * @return ApplyInfoShowDTO
	 * @throws
	 */
	ApplyResponseDTO queryApply(String userNumber);
	
	/**
	 * （银行、地区、一二级分类）编码查询
	 * @param queryCodeDTO
	 * @return String
	 * @throws
	 */
	QueryCodeResponseDTO queryCode(QueryCodeDTO queryCodeDTO);
	
	/**
	 * yop回调接收处理
	 * @param params
	 * @return String
	 * @throws
	 */
    BaseResponse processApplyCallbackFromYop(YOPCallBackDTO params);
}
