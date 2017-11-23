package com.shentu.g3.app.pay.chain.intercept;

import com.shentu.g3.app.pay.context.Context;
import com.shentu.g3.app.pay.http.WFrontResponse;

/**
 * 
 * @ClassName: Interceptor 
 * @Description: 拦截器
 * @author yunpeng.pan
 * @date 2017年9月18日 下午6:17:23 
 *
 */
public interface Interceptor {

	WFrontResponse intercept(Context context) throws Throwable;

    Interceptor getNext();

    void setNext(Interceptor interceptor);
}
