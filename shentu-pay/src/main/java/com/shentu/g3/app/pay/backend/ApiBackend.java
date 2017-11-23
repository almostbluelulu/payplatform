package com.shentu.g3.app.pay.backend;

/**
 * <pre>
 * 功能说明：api后端配置定义信息
 * </pre>
 *
 * @author wang.bao
 * @version 1.0
 */
public interface ApiBackend {

    Object invoke(String param) throws Throwable;
}