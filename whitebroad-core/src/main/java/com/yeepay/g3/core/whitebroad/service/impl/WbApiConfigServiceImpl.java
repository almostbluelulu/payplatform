package com.yeepay.g3.core.whitebroad.service.impl;

import com.yeepay.g3.core.whitebroad.entity.WbApiConfigEntity;
import com.yeepay.g3.core.whitebroad.repository.WbApiConfigDao;
import com.yeepay.g3.core.whitebroad.service.WbApiConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class WbApiConfigServiceImpl implements WbApiConfigService {
	
	@Resource
	private WbApiConfigDao wbApiConfigEntityDao;

	public int insert(WbApiConfigEntity pojo) {
		return wbApiConfigEntityDao.insert(pojo);
	}

	@Override
	public WbApiConfigEntity selectApiInfoByUri(String apiUri) {
		return wbApiConfigEntityDao.selectApiInfoByUri(apiUri);
	}

	@Override
	public List<WbApiConfigEntity> selectAllApi() {
		return wbApiConfigEntityDao.selectAllApi();
	}
}
