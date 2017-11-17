package com.shentu.g3.facade.whitebroad.facade;

import com.shentu.g3.facade.whitebroad.dto.BaseResponse;
import com.shentu.g3.facade.whitebroad.dto.apply.*;

/**
 * 入网接口
 *
 * @author hongyu.liu
 * @date 2017年9月18日 下午3:17:57
 */
public interface ApplyFacade {

	/**
	 * 图片上传
	 * @param uploadRequestDTO
	 * @return UploadResponseDTO
	 * @throws
	 */
	UploadResponseDTO fileUpload(UploadRequestDTO uploadRequestDTO);
	
	/**
	 * 入网
	 * @param applyRequestDTO
	 * @throws Throwable
	 * @return ApplyResponseDTO
	 * @throws
	 */
	ApplyResponseDTO apply(ApplyRequestDTO applyRequestDTO) throws Throwable;
	
	/**
	 * 入网查询
	 * @param userNumber
	 * @return AccountOpenInfoShowDTO
	 * @throws
	 */
	ApplyResponseDTO queryApply(ApplyRequestDTO applyRequestDTO);
	
	/**
	 * （银行、地区、一二级分类）编码查询
	 * @param queryCodeDTO
	 * @return String
	 * @throws Throwable
	 */
	QueryCodeResponseDTO queryCode(QueryCodeDTO queryCodeDTO) throws Throwable;
	
	/**
	 * yop回调接收
	 * @param params
	 * @return String
	 * @throws Throwable
	 */
    BaseResponse receiveApplyCallbackFromYop(YOPCallBackDTO params) throws Throwable;
}
