package com.shentu.g3.app.pay.chain.intercept;

import com.shentu.g3.app.pay.context.ApiContext;
import com.shentu.g3.app.pay.context.Context;
import com.shentu.g3.app.pay.exception.WFSysException;
import com.shentu.g3.app.pay.handler.ApiHandler;
import com.shentu.g3.app.pay.http.WFrontResponse;
import com.shentu.g3.app.pay.route.ApiRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("initializeApiContextInterceptor")
public class InitializeApiContextInterceptor extends AbstractInterceptor {

    @Autowired
    private ApiRouter apiRouter;

    public WFrontResponse onIntercept(Context context) throws Throwable {
        ApiContext apiContext = context.getApiContext();
        String apiUri = context.getApiUri();
        ApiHandler apiHandler = apiRouter.route(apiUri.toLowerCase());
        if (apiHandler == null) {
            logger.error("api doesn't exist,apiUri:" + apiUri);
            throw new WFSysException("1000200", "api不存在");
        }
        apiContext.setApiHandler(apiHandler);
        return getNext().intercept(context);
    }
}
