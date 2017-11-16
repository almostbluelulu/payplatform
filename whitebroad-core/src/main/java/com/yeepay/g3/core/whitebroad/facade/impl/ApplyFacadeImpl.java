package com.yeepay.g3.core.whitebroad.facade.impl;

import com.yeepay.g3.facade.whitebroad.dto.BaseResponse;
import com.yeepay.g3.facade.whitebroad.dto.apply.*;
import com.yeepay.g3.facade.whitebroad.exception.ErrorCode;
import com.yeepay.g3.facade.whitebroad.exception.WbSysException;
import com.yeepay.g3.facade.whitebroad.facade.ApplyFacade;
import org.springframework.stereotype.Service;

/**
 * 入网接口实现
 *
 * @author hongyu.liu
 * @date 2017年9月18日 下午3:25:46
 */
@Service("applyFacade")
public class ApplyFacadeImpl extends AbstractFacade implements ApplyFacade {

	@Override
	public UploadResponseDTO fileUpload(UploadRequestDTO uploadRequestDTO) {
		try {
			return applyBiz.fileUpload(uploadRequestDTO);
		} catch(Throwable t){
			if(t instanceof WbSysException){
				throw t;
			}
			throw new WbSysException(ErrorCode.SYSTEM_EXCEPTION, t);
		}
	}

	public ApplyResponseDTO apply(ApplyRequestDTO applyRequestDTO) throws Throwable {
		try {
			return applyBiz.apply(applyRequestDTO);
		} catch(Throwable t){
			if(t instanceof WbSysException){
				throw t;
			}
			throw new WbSysException(ErrorCode.SYSTEM_EXCEPTION, t);
		}
	}

	@Override
	public ApplyResponseDTO queryApply(ApplyRequestDTO applyRequestDTO) {
		try {
			return applyBiz.queryApply(applyRequestDTO.getUserNumber());
		} catch(Throwable t){
			if(t instanceof WbSysException){
				throw t;
			}
			throw new WbSysException(ErrorCode.SYSTEM_EXCEPTION, t);
		}
	}

	@Override
	public QueryCodeResponseDTO queryCode(QueryCodeDTO queryCodeDTO) throws Throwable {
		try {
			return applyBiz.queryCode(queryCodeDTO);
		} catch(Throwable t){
			if(t instanceof WbSysException){
				throw t;
			}
			throw new WbSysException(ErrorCode.SYSTEM_EXCEPTION, t);
		}
	}

	@Override
    public BaseResponse receiveApplyCallbackFromYop(YOPCallBackDTO params) throws Throwable {
        try {
			return applyBiz.processApplyCallbackFromYop(params);
		} catch(Throwable t){
			if(t instanceof WbSysException){
				throw t;
			}
			throw new WbSysException(ErrorCode.SYSTEM_EXCEPTION, t);
		}
	}

}
