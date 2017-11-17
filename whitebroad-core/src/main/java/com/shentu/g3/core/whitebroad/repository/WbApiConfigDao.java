package com.shentu.g3.core.whitebroad.repository;

import java.util.List;

import com.shentu.g3.core.whitebroad.entity.WbApiConfigEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
