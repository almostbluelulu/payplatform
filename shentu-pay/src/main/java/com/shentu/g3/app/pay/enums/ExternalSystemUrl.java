package com.shentu.g3.app.pay.enums;

public enum ExternalSystemUrl {

	USERAUTH_FACADE_URL("user auth facade", "http://127.0.0.1:8082/whitebroad-hessian/hessian/WbUserAuthFacade"),
	API_FACADE_URL("api facade", "http://127.0.0.1:8082/whitebroad-hessian/hessian/WbApiInfoFacade");
//	QA：
//	USERAUTH_FACADE_URL("user auth facade", "http://127.0.0.1:8080/whitebroad-hessian/hessian/WbUserAuthFacade"),
//	API_FACADE_URL("api facade", "http://127.0.0.1:8080/whitebroad-hessian/hessian/WbApiInfoFacade");


	private String serviceName;

	private String serviceURL;

	ExternalSystemUrl(String serviceName, String serviceURL) {
		this.serviceName = serviceName;
		this.serviceURL = serviceURL;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public String getServiceURL() {
		return this.serviceURL;
	}

}