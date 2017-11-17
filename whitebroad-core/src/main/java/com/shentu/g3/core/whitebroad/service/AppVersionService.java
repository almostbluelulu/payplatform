package com.shentu.g3.core.whitebroad.service;

import com.shentu.g3.core.whitebroad.entity.AppVersionEntity;
import com.shentu.g3.facade.whitebroad.enumtype.AppRoleEnum;
import com.shentu.g3.facade.whitebroad.enumtype.VersionPlatform;

/**
 * @Description: 版本service
 * @Author: zhaoyu.cui
 * @Date: 16/10/26
 * @Time: 下午2:17
 */
public interface AppVersionService {

	/**
	 * 创建新版
	 *
	 * @param appVersionEntity
	 */
	void save(AppVersionEntity appVersionEntity);

	/**
	 * 根据平台和用户查询最新版（app）
	 *
	 * @param platformEnum
	 * @param roleEnum
	 * @return
	 */
	AppVersionEntity findNewByRoleAndPlatform(VersionPlatform platformEnum, AppRoleEnum roleEnum);

	/**
	 * 跟新url、操作员等上传信息
	 *
	 * @param appVersionEntity
	 */
	void updateUrl(AppVersionEntity appVersionEntity);

	/**
	 * 根据版本id查询
	 *
	 * @param id
	 * @return
	 */
	AppVersionEntity findById(String id);
}
