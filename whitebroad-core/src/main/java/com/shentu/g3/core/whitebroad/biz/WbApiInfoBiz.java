package com.shentu.g3.core.whitebroad.biz;

import java.util.List;

import com.shentu.g3.facade.whitebroad.dto.api.ApiInfoDTO;

/**
 * 
 * @ClassName: WbApiConfigService 
 * @Description: api配置信息biz
 * @author yunpeng.pan
 * @date 2017年9月18日 下午2:42:33 
 *
 */
public interface WbApiInfoBiz{

	/**
	 * 
	 * @Description: 根据uri查询api详情
	 * @param @param apiUri
	 * @param @return 
	 * @return ApiInfoDTO 返回类型 
	 * @author yunpeng.pan
	 * @date 2017年9月19日 下午5:41:09
	 */
	ApiInfoDTO findApiInfoByUri(String apiUri);

	/**
	 * 
	 * @Description: 获取所有uri信息 
	 * @param @return 
	 * @return List<ApiInfoDTO> 返回类型 
	 * @author yunpeng.pan
	 * @date 2017年9月19日 下午5:41:33
	 */
	List<ApiInfoDTO> queryAllApi();

}