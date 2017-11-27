package com.shentu.g3.core.whitebroad.service;

import java.util.List;

import com.shentu.g3.core.whitebroad.entity.WbApiConfigEntity;

/**
 * 
 * @ClassName: WbApiConfigService 
 * @Description: api配置service
 * @author yunpeng.pan
 * @date 2017年9月18日 下午2:42:33 
 *
 */
public interface WbApiConfigService{

	WbApiConfigEntity selectApiInfoByUri(String apiUri);

	List<WbApiConfigEntity> selectAllApi();

}