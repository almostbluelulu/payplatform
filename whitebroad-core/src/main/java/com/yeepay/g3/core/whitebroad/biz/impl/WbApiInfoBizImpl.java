package com.yeepay.g3.core.whitebroad.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.yeepay.g3.core.whitebroad.biz.AbstractBiz;
import com.yeepay.g3.core.whitebroad.biz.WbApiInfoBiz;
import com.yeepay.g3.core.whitebroad.entity.WbApiConfigEntity;
import com.yeepay.g3.core.whitebroad.parser.impl.ApiConfigConvert;
import com.yeepay.g3.facade.whitebroad.dto.api.ApiInfoDTO;
import com.yeepay.g3.facade.whitebroad.exception.ErrorCode;
import com.yeepay.g3.facade.whitebroad.exception.WbSysException;

@Component
public class WbApiInfoBizImpl extends AbstractBiz implements WbApiInfoBiz {

	@Override
	public ApiInfoDTO findApiInfoByUri(String apiUri) {
		if(StringUtils.isEmpty(apiUri)) {
			throw new WbSysException(ErrorCode.PARAM_EXCEPTION);
		}
		WbApiConfigEntity tokenEntity = wbApiConfigService.selectApiInfoByUri(apiUri);
		return new ApiConfigConvert().convert2DTO(tokenEntity);
	}

	@Override
	public List<ApiInfoDTO> queryAllApi() {
		List<WbApiConfigEntity> tokenEntityList = wbApiConfigService.selectAllApi();
		List<ApiInfoDTO> apiInfoList = new ArrayList<ApiInfoDTO>();
		for(WbApiConfigEntity entity : tokenEntityList) {
			ApiInfoDTO dto = new ApiConfigConvert().convert2DTO(entity);
			apiInfoList.add(dto);
		}
		return apiInfoList;
	}

}
