package com.shentu.g3.app.pay.chain.intercept;

import org.springframework.stereotype.Component;

import com.shentu.g3.app.pay.context.Context;
import com.shentu.g3.app.pay.http.WFrontResponse;

/**
 * title: <br/>
 * description: 真正远程业务逻辑调用<br/>
 * Copyright: Copyright (c)2014<br/>
 * Company: 易宝支付(YeePay)<br/>
 */
@Component("apiHandleInterceptor")
public class ApiHandleInterceptor extends AbstractInterceptor {

    @Override
    public WFrontResponse onIntercept(Context context) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return context.getApiContext().getApiHandler().handle(context.getData());
        } finally {
            context.getProperties().put("backendLatency", System.currentTimeMillis() - start);
        }
    }

}
