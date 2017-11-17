package com.shentu.g3.core.whitebroad.facade.impl;

import org.springframework.stereotype.Service;

import com.shentu.g3.facade.whitebroad.dto.auth.UserAuthDTO;
import com.shentu.g3.facade.whitebroad.facade.WbUserAuthFacade;

@Service("wbUserAuthFacade")
public class WbUserAuthFacadeImpl extends AbstractFacade implements WbUserAuthFacade {

	@Override
	public UserAuthDTO findUserAuthInfo(String userToken) {
		return wbUserAuthBiz.queryUserAuthInfo(userToken);
	}

	@Override
	public UserAuthDTO testTransfer(UserAuthDTO dto) {
		System.err.println("invoke testTransfer success. userNumber="+ dto.getUserNumber());
		dto.setPhoneNumber("18611221986");
		return dto;
	}
	
	@Override
	public UserAuthDTO testNoToken(UserAuthDTO dto) {
		System.err.println("invoke testNoToken success. userNumber="+ dto.getUserNumber());
		dto.setPhoneNumber("4008208888");
		return dto;
	}
	
}