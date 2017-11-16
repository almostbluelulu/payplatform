package com.yeepay.g3.core.whitebroad.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: WbApiConfig 
 * @Description: api配置
 * @author yunpeng.pan
 * @date 2017年9月18日 下午2:42:33 
 *
 */
public class WbApiConfigEntity implements Serializable{

	private static final long serialVersionUID = -553205879085855053L;
	
	/** 主键id */
	private Long id;
	
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
	
	/** 描述 */
	private String description;
	
	/** 操作人 */
	private String operator;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 更新时间 */
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getApiHost() {
		return apiHost;
	}

	public void setApiHost(String apiHost) {
		this.apiHost = apiHost;
	}
}
