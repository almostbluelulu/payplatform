package com.yeepay.g3.core.whitebroad.repository;

import com.yeepay.g3.core.whitebroad.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    int insert(@Param("pojo") UserEntity pojo);

    int insertList(@Param("pojos") List<UserEntity> pojo);

    int update(@Param("pojo") UserEntity pojo);

    UserEntity findById(@Param("id") Long id);

    UserEntity findByUserNo(@Param("userNo") String userNo);

    UserEntity findByPhoneNo(@Param("phoneNumber") String phoneNo);

    List<UserEntity> list();

    Long getSequence();
}
