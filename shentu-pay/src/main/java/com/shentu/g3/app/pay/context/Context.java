package com.shentu.g3.app.pay.context;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shentu.g3.app.pay.enums.FormatType;

/**
 * 上下文信息
 * 1.AppContext：包含本次请求的应用相关的信息，比如appKey和appSecret等。AppSecretManager可以修改AppContext信息。
 * 2.ApiContext：包含本次请求的API相关的信息，比如uri，handler，definition，backend等。ApiRouter可以通过路由更改ApiContext信息。
 * @ClassName: Context 
 * @author yunpeng.pan
 * @date 2017年9月18日 下午4:50:05 
 *
 * @param <A>
 */
public interface Context {

    /**
     * requestId
     */
    String getRequestId();

    /**
     * 请求api上下文
     */
    ApiContext getApiContext();

    /**
     * 原始请求request对象
     */
    HttpServletRequest getRawRequest();

    /**
     * 原始请求response对象
     */
    HttpServletResponse getRawResponse();

    /**
     * 请求源ip
     */
    String getSourceIp();

    /**
     * 格式
     */
    FormatType getFormat();

    /**
     * 自定义属性
     */
    Map<String, Object> getProperties();

    /**
     * 用户token
     */
	String getUserToken();

	/**
	 * 请求uri
	 */
	String getApiUri();
	
	/**
	 * 请求数据
	 */
	String getData();
	
	void setData(String data);
	
	/**
	 * 获取aes秘钥
	 */
	byte[] getAesKey();

	void setAesKey(byte[] aesKey);
}
