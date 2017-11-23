package com.shentu.g3.app.pay.backend;

import com.shentu.g3.app.pay.exception.WFSysException;
import com.shentu.g3.app.pay.util.DataConvertUtil;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;

/**
 * <pre>
 * 功能说明：后端为方法调用，包括远程调用，本地方法调用
 * </pre>
 *
 * @author wang.bao
 * @version 1.0
 */
public class MethodInvokeBackend implements ApiBackend {
    /**
     * 端点服务方法
     */
    protected Method endMethod;

    /**
     * 端点服务代理
     */
    protected Object proxy;

    public MethodInvokeBackend() {
        super();
    }

    public Method getEndMethod() {
        return endMethod;
    }

    public void setEndMethod(Method endMethod) {
        this.endMethod = endMethod;
    }

    public void setProxy(Object proxy) {
        this.proxy = proxy;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Object invoke(String params) throws Throwable {
    	
        if (StringUtils.isBlank(params) || endMethod.getParameterTypes().length < 1) {
            return endMethod.invoke(proxy, null);
        }
        Object[] args = new Object[endMethod.getParameterTypes().length];
        int i=0;
        try {
	        for(Class clazz : endMethod.getParameterTypes()) {
	        	args[i++] = DataConvertUtil.fromJson(params, clazz);
	        }
        } catch (Exception e) {
            throw new WFSysException("1000201", "非法请求参数");
        }
        return endMethod.invoke(proxy, args);
    }
}
