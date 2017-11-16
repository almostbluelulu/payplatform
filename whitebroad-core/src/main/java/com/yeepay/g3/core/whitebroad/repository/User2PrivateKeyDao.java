package com.yeepay.g3.core.whitebroad.repository;

import com.yeepay.g3.core.whitebroad.entity.User2PrivateKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User2PrivateKeyDao {
    int insert(@Param("pojo") User2PrivateKey pojo);

    int insertSelective(@Param("pojo") User2PrivateKey pojo);

    int insertList(@Param("pojos") List<User2PrivateKey> pojo);

    int update(@Param("pojo") User2PrivateKey pojo);

    User2PrivateKey getByUserNumber(@Param("userNumber") String userNumber);
}
