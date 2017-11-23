package com.shentu.g3.app.pay.context;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.shentu.g3.app.pay.enums.FormatType;

/**
 * 
 * @ClassName: StandardContext 
 * @Description: web上下文
 * @author yunpeng.pan
 * @date 2017年9月19日 上午11:21:23 
 *
 */
public class StandardContext implements Context {

    private static final String REQUEST_ID_HEADER = "X-WF-Request-ID";
    private static final String REQUEST_TOKEN = "token";
    private static final String REQUEST_URI = "uri";
    private static final String REQUEST_DATA = "data";

    private String requestId;

    private HttpServletRequest rawRequest;
    
    private HttpServletResponse rawResponse;

    private ApiContext apiContext;
    
    private FormatType format;
    
    private String userToken;
    
    private String apiUri;

    private String data;
    
    private byte[] aesKey;
    
    private String sourceIp;

    private String logString;

    private Map<String, Object> properties = new HashMap<String, Object>();

    public StandardContext(HttpServletRequest request, HttpServletResponse response) {
        rawRequest = request;
        rawResponse = response;

//      sourceIp = NetworkUtils.getRemoteHost(request);
        format = FormatType.json;

        apiContext = new StandardApiContext(request);
        apiUri = request.getParameter(REQUEST_URI);
        data = request.getParameter(REQUEST_DATA);
       	userToken = request.getParameter(REQUEST_TOKEN);
        requestId = request.getHeader(REQUEST_ID_HEADER);
        requestId = StringUtils.isBlank(requestId) ? UUID.randomUUID().toString().replaceAll("-", "") : requestId;

        logString = "requestId:" + requestId +
                	",apiUri:" + apiUri +
                	",userToken:" + userToken +
                	",data:" + data;
    }

    public FormatType getFormat() {
        return format;
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    @Override
    public ApiContext getApiContext() {
        return apiContext;
    }

    public HttpServletRequest getRawRequest() {
        return rawRequest;
    }

    public HttpServletResponse getRawResponse() {
        return rawResponse;
    }

    public String getRequestId() {
        return requestId;
    }

    public String toString() {
        return logString;
    }
    
    @Override
	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	
	@Override
	public String getApiUri() {
		return apiUri;
	}

	public void setApiUri(String apiUri) {
		this.apiUri = apiUri;
	}
	
	@Override
	public String getData() {
		return data;
	}
	
	@Override
	public void setData(String data) {
		this.data = data;
	}

	@Override
	public byte[] getAesKey() {
		return aesKey;
	}

	@Override
	public void setAesKey(byte[] aesKey) {
		this.aesKey = aesKey;
	}
}