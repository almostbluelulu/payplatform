package com.shentu.g3.facade.whitebroad.facade;

import com.shentu.g3.facade.whitebroad.dto.auth.UserAuthDTO;

/**
 * 
 * @ClassName: WbUserAuthFacade 
 * @Description: 用户鉴权接口
 * @author yunpeng.pan
 * @date 2017年9月18日 下午3:32:22 
 *
 */
public interface WbUserAuthFacade {
	
	UserAuthDTO testTransfer(UserAuthDTO dto);
	
	UserAuthDTO testNoToken(UserAuthDTO dto);
	
	/**
	 * 获取用户token信息
	 */
	UserAuthDTO findUserAuthInfo(String userToken);
}
