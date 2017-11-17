package com.shentu.g3.core.whitebroad.biz;

import com.shentu.g3.facade.whitebroad.dto.auth.UserAuthDTO;

/**
 * 
 * @ClassName: WbUserAuthServiceBiz 
 * @Description: 用户鉴权biz
 * @author yunpeng.pan
 * @date 2017年9月18日 下午2:42:33 
 *
 */
public interface WbUserAuthBiz{

	/**
	 * 查询用户token信息
	 *
	 * @param userToken
	 * @return
	 */
	UserAuthDTO queryUserAuthInfo(String userToken);
}