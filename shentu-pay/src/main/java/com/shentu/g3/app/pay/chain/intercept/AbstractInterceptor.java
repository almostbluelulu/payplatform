package com.shentu.g3.app.pay.chain.intercept;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shentu.g3.app.pay.context.Context;
import com.shentu.g3.app.pay.http.WFrontResponse;

/**
 * title: <br/>
 * description: 描述<br/>
 * Copyright: Copyright (c)2014<br/>
 * Company: 易宝支付(YeePay)<br/>
 *
 * @author wenkang.zhang
 * @version 1.0.0
 * @since 16/10/25 下午6:03
 */
public abstract class AbstractInterceptor implements Interceptor {

    protected final Log logger = LogFactory.getLog(getClass());

    protected Interceptor next;

    private String name = this.getClass().getSimpleName();

    protected void beforeIntercept(Context context) {
        if (logger.isDebugEnabled()) {
            logger.debug("[monitor],eventType: enter_interceptor," + context + ",interceptor_name:"+name);
        }
    }

    protected void afterIntercept(Context context) {
        if (logger.isDebugEnabled()) {
            logger.debug("[monitor],eventType: leave_interceptor," + context + ",interceptor_name:"+name);
        }
    }

    public WFrontResponse intercept(Context context) throws Throwable {
        beforeIntercept(context);
        WFrontResponse result = onIntercept(context);
        afterIntercept(context);
        return result;
    }

    protected abstract WFrontResponse onIntercept(Context context) throws Throwable;

    @Override
    public Interceptor getNext() {
        return next;
    }

    @Override
    public void setNext(Interceptor interceptor) {
        next = interceptor;
    }

    public final String getName() {
        return name;
    }
}
