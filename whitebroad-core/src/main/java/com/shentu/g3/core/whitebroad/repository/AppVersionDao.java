package com.shentu.g3.core.whitebroad.repository;

import com.shentu.g3.core.whitebroad.entity.AppVersionEntity;
import com.shentu.g3.facade.whitebroad.enumtype.AppRoleEnum;
import com.shentu.g3.facade.whitebroad.enumtype.VersionPlatform;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppVersionDao {

	int save(@Param("pojo") AppVersionEntity record);

	AppVersionEntity findById(@Param("id") String id);

	AppVersionEntity findNewByRoleAndPlatform(@Param("platform") VersionPlatform platformEnum,
											  @Param("roleType") AppRoleEnum roleEnum);

	int update(@Param("pojo") AppVersionEntity record);

	long nextSequence();
}