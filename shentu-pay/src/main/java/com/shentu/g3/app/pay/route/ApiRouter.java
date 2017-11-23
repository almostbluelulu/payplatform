package com.shentu.g3.app.pay.route;

import com.shentu.g3.app.pay.handler.ApiHandler;

/**
 * 
 * @ClassName: ApiRouter 
 * @Description: api路由器
 * @author yunpeng.pan
 * @date 2017年9月18日 下午4:35:54 
 *
 */
public interface ApiRouter {

    /**
     * 路由信息初始化
     */
    void initialize();

    /**
     * 注册路由信息，该注册执行以下动作：
     * 1.如果uri对应的路由信息不存在，则创建之;
     * 2.如果uri对应的路由信息已经存在，则覆盖之;
     * 3.如果handler为null，则删除之；
     */
    void registerRoute(String uri, ApiHandler handler);

    /**
     * 路由到合适的ApiHandler来处理uri
     */
    ApiHandler route(String uri);
}
