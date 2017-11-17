package com.shentu.g3.core.whitebroad.service.impl;

import com.shentu.g3.core.whitebroad.entity.AppVersionEntity;
import com.shentu.g3.core.whitebroad.repository.AppVersionDao;
import com.shentu.g3.core.whitebroad.service.AppVersionService;
import com.shentu.g3.core.whitebroad.util.BizUidUtil;
import com.shentu.g3.facade.whitebroad.enumtype.AppRoleEnum;
import com.shentu.g3.facade.whitebroad.enumtype.BizPrefixEnum;
import com.shentu.g3.facade.whitebroad.enumtype.VersionPlatform;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * @Description: app版本service
 * @Author: jiawen.huang
 * @Date: 16/10/26
 * @Time: 下午2:21
 */
@Service("appVersionService")
public class AppVersionServiceImpl implements AppVersionService {

	@Autowired
	private AppVersionDao appVersionDao;

	@Override
	public void save(AppVersionEntity appVersionEntity) {
		try {
			String id = BizUidUtil.generateBizUID(BizPrefixEnum.AP.getValue(), appVersionDao.nextSequence());//生成编号
			appVersionEntity.setId(StringUtils.isBlank(appVersionEntity.getId()) ? id : appVersionEntity.getId());
			appVersionDao.save(appVersionEntity);
		} catch (DuplicateKeyException e) {
			throw new WbSysException(ErrorCode.APP_VERSION_EXIST, e);
		}
	}

	@Override
	public AppVersionEntity findNewByRoleAndPlatform(VersionPlatform platformEnum, AppRoleEnum roleEnum) {
		return appVersionDao.findNewByRoleAndPlatform(platformEnum, roleEnum);
	}

	@Override
	public void updateUrl(AppVersionEntity appVersionEntity) {
		Integer num = appVersionDao.update(appVersionEntity);
		if (0 == num) {
			throw new WbSysException(ErrorCode.APP_VERSION_NO_EXIST);
		}
	}

	@Override
	public AppVersionEntity findById(String id) {
		AppVersionEntity appVersionEntity = appVersionDao.findById(id);
		if (null == appVersionEntity) {
			throw new WbSysException(ErrorCode.APP_VERSION_NO_EXIST);
		}
		return appVersionEntity;
	}
}
