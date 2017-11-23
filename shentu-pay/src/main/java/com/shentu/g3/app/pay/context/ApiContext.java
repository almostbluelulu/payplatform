package com.shentu.g3.app.pay.context;

import com.shentu.g3.app.pay.handler.ApiHandler;

/**
 * 
 * @ClassName: ApiContext 
 * @Description: api上下文
 * @author yunpeng.pan
 * @date 2017年9月19日 上午11:26:31 
 *
 */
public interface ApiContext {

    ApiHandler getApiHandler();

    void setApiHandler(ApiHandler apiHandler);

    String getApiUri();
}