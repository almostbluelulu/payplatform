package com.yeepay.g3.core.whitebroad.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.whitebroad.entity.WbUserTokenEntity;

/**
 * 
 * @ClassName: WbApiConfigDao 
 * @Description: api配置dao
 * @author yunpeng.pan
 * @date 2017年9月18日 下午2:42:33 
 *
 */
@Repository
public interface WbUserTokenDao {
    int insert(@Param("pojo") WbUserTokenEntity pojo);

    int updateToken(@Param("pojo") WbUserTokenEntity pojo);

    int updateExpireTime(@Param("pojo") WbUserTokenEntity pojo);

    WbUserTokenEntity selectUserToken(@Param("userToken") String userToken);

	WbUserTokenEntity selectUserTokenByUserNo(@Param("userNo") String userNo);

	int revokeToken(@Param("pojo") WbUserTokenEntity pojo);

}
