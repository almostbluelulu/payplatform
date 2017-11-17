package com.shentu.g3.facade.whitebroad.dto.api;


import com.shentu.g3.facade.whitebroad.dto.BaseResponse;

/**
 * 
 * @ClassName: UserAuthDTO 
 * @Description: 用户鉴权DTO
 * @author yunpeng.pan
 * @date 2017年9月18日 下午8:18:29 
 *
 */
public class ApiInfoDTO extends BaseResponse {

	private static final long serialVersionUID = 3142300477178846980L;

	/** 应用名称 */
	private String apiName;

	/** 应用host */
	private String apiHost;
	
	/** 调用uri */
	private String apiUri;
	
	/** 后台类名 */
	private String backendClass;
	
	/** 方法名 */
	private String backendMethod;
	
	/** 是否需要登录 */
	private Boolean needSession;
	
	/** 是否可用 */
	private Boolean available;
	
	/** 参数验证规则 */
	private String paramValidRule;

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getApiHost() {
		return apiHost;
	}

	public void setApiHost(String apiHost) {
		this.apiHost = apiHost;
	}

	public String getApiUri() {
		return apiUri;
	}

	public void setApiUri(String apiUri) {
		this.apiUri = apiUri;
	}

	public String getBackendClass() {
		return backendClass;
	}

	public void setBackendClass(String backendClass) {
		this.backendClass = backendClass;
	}

	public String getBackendMethod() {
		return backendMethod;
	}

	public void setBackendMethod(String backendMethod) {
		this.backendMethod = backendMethod;
	}

	public Boolean getNeedSession() {
		return needSession;
	}

	public void setNeedSession(Boolean needSession) {
		this.needSession = needSession;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getParamValidRule() {
		return paramValidRule;
	}

	public void setParamValidRule(String paramValidRule) {
		this.paramValidRule = paramValidRule;
	}

}