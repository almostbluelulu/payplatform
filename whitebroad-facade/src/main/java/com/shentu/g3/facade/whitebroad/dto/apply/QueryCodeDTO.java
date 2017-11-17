package com.shentu.g3.facade.whitebroad.dto.apply;

import com.shentu.g3.facade.whitebroad.dto.BaseRequest;

/**
 * （地区、银行、一二级分类）编码查询
 *
 * @author hongyu.liu
 * @date 2017年9月20日 下午2:48:12
 */
public class QueryCodeDTO extends BaseRequest {

	private static final long serialVersionUID = 3282807724085199083L;

	/**
	 * 编码类型
	 */
	private String codeType;
	
	/**
	 * 编码
	 */
	private String code;

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
