package com.yeepay.g3.facade.whitebroad.dto.apply;

import com.yeepay.g3.facade.whitebroad.dto.BaseResponse;

/**
 * 编码查询出参
 *
 * @author hongyu.liu
 * @date 2017年9月28日 上午9:58:55
 */
public class QueryCodeResponseDTO extends BaseResponse {

	private static final long serialVersionUID = 4857235859390264494L;

	/**
	 * 编码查询结果
	 */
	private String queryResult;

	public String getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(String queryResult) {
		this.queryResult = queryResult;
	}

}
