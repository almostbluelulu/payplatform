package com.shentu.g3.app.pay.chain;


import com.shentu.g3.app.pay.chain.intercept.Interceptor;

/**
 * 
 * @ClassName: InterceptorChain 
 * @Description: 拦截器链表
 * @author yunpeng.pan
 * @date 2017年9月18日 下午6:19:14 
 *
 */
public interface InterceptorChain {

	/** 获取头部拦截器 */
    Interceptor getHead();

    /** 添加拦截器 */
    void addInterceptor(Interceptor interceptor);
}
