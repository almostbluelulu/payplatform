package com.yeepay.g3.core.whitebroad.biz.impl;


import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.yeepay.g3.core.whitebroad.biz.AbstractBiz;
import com.yeepay.g3.core.whitebroad.biz.WbUserAuthBiz;
import com.yeepay.g3.core.whitebroad.entity.WbUserTokenEntity;
import com.yeepay.g3.core.whitebroad.parser.impl.UserTokenConvert;
import com.yeepay.g3.facade.whitebroad.dto.auth.UserAuthDTO;
import com.yeepay.g3.facade.whitebroad.exception.ErrorCode;
import com.yeepay.g3.facade.whitebroad.exception.WbSysException;

@Component
public class WbUserAuthBizImpl extends AbstractBiz implements WbUserAuthBiz {
	
	@Override
	public UserAuthDTO queryUserAuthInfo(String userToken) {
		if(StringUtils.isEmpty(userToken)) {
			throw new WbSysException(ErrorCode.PARAM_EXCEPTION);
		}
		WbUserTokenEntity userTokenEntity = wbUserTokenService.selectUserToken(userToken);
		UserTokenConvert utc = new UserTokenConvert();
		return utc.convert2DTO(userTokenEntity);
	}
	
}