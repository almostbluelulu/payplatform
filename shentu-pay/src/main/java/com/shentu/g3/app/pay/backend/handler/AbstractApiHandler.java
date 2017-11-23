package com.shentu.g3.app.pay.backend.handler;

import com.shentu.g3.app.pay.backend.ApiBackend;
import com.shentu.g3.app.pay.handler.ApiHandler;
import com.shentu.g3.app.pay.http.WFrontResponse;
import com.yeepay.g3.facade.whitebroad.dto.api.ApiInfoDTO;

public class AbstractApiHandler implements ApiHandler {

    private ApiInfoDTO apiDefinition;
    private ApiBackend backend;

    public AbstractApiHandler(ApiInfoDTO apiDefinition, ApiBackend backend) {
        this.apiDefinition = apiDefinition;
        this.backend = backend;
    }

    @Override
    public WFrontResponse handle(String jsonParams) throws Throwable {
        return WFrontResponse.newResultInstance(backend.invoke(jsonParams));
    }

    public ApiInfoDTO getApiDefinition() {
        return apiDefinition;
    }

}