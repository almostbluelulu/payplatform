package com.yeepay.g3.core.whitebroad.service.impl;

import com.yeepay.g3.core.whitebroad.entity.User2PrivateKey;
import com.yeepay.g3.core.whitebroad.repository.User2PrivateKeyDao;
import com.yeepay.g3.core.whitebroad.service.User2PrivateKeyService;
import com.yeepay.g3.facade.whitebroad.exception.ErrorCode;
import com.yeepay.g3.facade.whitebroad.exception.WbSysException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class User2PrivateKeyServiceImpl implements User2PrivateKeyService {

    @Resource
    private User2PrivateKeyDao user2PrivateKeyDao;

    public void insert(User2PrivateKey pojo){
        try {
            user2PrivateKeyDao.insert(pojo);
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.CREATE_FAILED,e);
        }
    }


    public void insertList(List<User2PrivateKey> pojos){
        try {
            user2PrivateKeyDao.insertList(pojos);
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.CREATE_FAILED,e);
        }
    }

    public void update(User2PrivateKey pojo){
        try {
            user2PrivateKeyDao.update(pojo);
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.UPDATE_FAILED,e);
        }
    }

    public  User2PrivateKey getByUserNumber(String userNumber){
        User2PrivateKey user2PrivateKey = null;
        try {
             user2PrivateKey = user2PrivateKeyDao.getByUserNumber(userNumber);
             if(null == user2PrivateKey){
                 throw new WbSysException(ErrorCode.USERKEY_NOT_EXIST_EXCEPTION);
             }
        } catch (Exception e) {
            throw new WbSysException(ErrorCode.SELECT_EXCEPTION,e);
        }
        return user2PrivateKey;
    }


}
