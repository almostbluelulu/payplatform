package com.shentu.g3.app.pay.chain;

import javax.annotation.PostConstruct;

import com.shentu.g3.app.pay.chain.intercept.Interceptor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: StandardInterceptorChain 
 * @Description: 拦截器链默认实现
 * @author yunpeng.pan
 * @date 2017年9月18日 下午6:20:40 
 *
 */
@Component
public class StandardInterceptorChain implements InterceptorChain, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Interceptor head = null;
    private Interceptor tail = null;

    /*
     * interceptors:
     * --------------------------------------------
     *
     *    1.initializeApiContextInterceptor(api初始化拦截器)
     *    根据用户传入的appUri，初始化对影的api信息。
     *    会路由寻找api处理器apiHandler，api定义信息apiDefinition，
     *    api后端apiBackend等。
     *    如api不存在或不可用，会抛出相应错误码异常：
     *
     *    99001002：服务不存在
     *
     * -------------------------------------------------------
     * 
     *    2.authenticateInterceptor(身份认证拦截器)
     *    通过解密和验签来验证用户身份
     *    如果解密或验签失败，会抛出相应错误码异常：
     *
     *    99001020：解密失败
     *    99001008：签名无效
     *
     * -------------------------------------------------------
     *
     *    3.apiHandleInterceptor(业务调用拦截器)
     *    什么都不做，只是调用一下真正的业务处理逻辑，
     *    应该始终是最后一个拦截器，将业务调用放在拦截器，是因为可以使整个处理流程更统一，类似于tomcat基础阀
     *
     * -------------------------------------------------------
     */
    private static final String INTERCEPTORS[] = {
            "initializeApiContextInterceptor",
            "authenticateInterceptor",
            "apiHandleInterceptor"
    };

	@PostConstruct
    public void initializeInterceptorChain() {
        for (String i : INTERCEPTORS) {
            Object o = applicationContext.getBean(i);
            if (!(o instanceof Interceptor)) {
                throw new IllegalArgumentException(i + " is not an instance of interceptor.");
            }
            addInterceptor((Interceptor) o);
        }
    }

    @Override
    public Interceptor getHead() {
        return head;
    }

    @Override
    public void addInterceptor(Interceptor interceptor) {
        if (head == null) {
            head = tail = interceptor;
            return;
        }
        tail.setNext(interceptor);
        tail = interceptor;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
