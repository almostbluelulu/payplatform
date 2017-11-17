package com.shentu.g3.facade.whitebroad.facade;

import java.util.List;

import com.shentu.g3.facade.whitebroad.dto.api.ApiInfoDTO;

/**
 * 
 * @ClassName: WbApiInfoFacade 
 * @Description: api信息接口
 * @author yunpeng.pan
 * @date 2017年9月18日 下午3:31:35 
 *
 */
public interface WbApiInfoFacade {

	/**
	 * 
	 * @Description: 通过apiUri查询
	 * @param @param apiUri
	 * @param @return 
	 * @return ApiInfoDTO 返回类型 
	 * @author yunpeng.pan
	 * @date 2017年9月19日 下午12:02:47
	 */
	ApiInfoDTO findApiInfoByUri(String apiUri);

	/**
	 * 
	 * @Description: 获取所有可用api
	 * @param @return 
	 * @return List<ApiInfoDTO> 返回类型 
	 * @author yunpeng.pan
	 * @date 2017年9月19日 下午2:32:20
	 */
	List<ApiInfoDTO> queryAllApi();
}
