package com.shentu.g3.app.pay.handler;

import com.shentu.g3.app.pay.http.WFrontResponse;
import com.yeepay.g3.facade.whitebroad.dto.api.ApiInfoDTO;

public interface ApiHandler {

	WFrontResponse handle(String jsonParams) throws Throwable;

	ApiInfoDTO getApiDefinition();
}
