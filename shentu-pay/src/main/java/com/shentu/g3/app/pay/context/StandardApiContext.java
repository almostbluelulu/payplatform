package com.shentu.g3.app.pay.context;

import javax.servlet.http.HttpServletRequest;

import com.shentu.g3.app.pay.handler.ApiHandler;

public class StandardApiContext implements ApiContext {

    private ApiHandler apiHandler;
    private String apiUri;

    @Override
    public ApiHandler getApiHandler() {
        return apiHandler;
    }

    @Override
    public void setApiHandler(ApiHandler apiHandler) {
        this.apiHandler = apiHandler;
    }

    @Override
    public String getApiUri() {
        return apiUri;
    }

    StandardApiContext(HttpServletRequest request) {
        apiUri = request.getParameter("uri");
    }
}