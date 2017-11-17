package com.shentu.g3.core.whitebroad.facade.impl;

import com.shentu.g3.facade.whitebroad.dto.api.ApiInfoDTO;
import com.shentu.g3.facade.whitebroad.facade.WbApiInfoFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("wbApiInfoFacade")
public class WbApiInfoFacadeImpl extends AbstractFacade implements WbApiInfoFacade {

	@Override
	public ApiInfoDTO findApiInfoByUri(String apiUri) {
		return wbApiInfoBiz.findApiInfoByUri(apiUri);
	}

	@Override
	public List<ApiInfoDTO> queryAllApi() {
		return wbApiInfoBiz.queryAllApi();
	}
}
