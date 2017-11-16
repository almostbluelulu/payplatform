package com.yeepay.g3.core.whitebroad.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.whitebroad.entity.WbApiConfigEntity;

/**
 *
 * @ClassName: WbApiConfigDao
 * @Description: api配置dao
 * @author yunpeng.pan
 * @date 2017年9月18日 下午2:42:33 
 *
 */
@Repository
public interface WbApiConfigDao {
	
    int insert(@Param("pojo") WbApiConfigEntity pojo);

	WbApiConfigEntity selectApiInfoByUri(@Param("apiUri") String apiUri);

	List<WbApiConfigEntity> selectAllApi();
}
