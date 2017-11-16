package com.yeepay.g3.core.whitebroad.repository;

import com.yeepay.g3.core.whitebroad.entity.qrcode.QrCode;
import com.yeepay.g3.facade.whitebroad.enumtype.qrcode.Status;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QrCodeDao {
    void insert(@Param("pojo") QrCode pojo);

    void insertList(@Param("pojos") List<QrCode> pojo);

    void update(@Param("pojo") QrCode pojo);

    List<QrCode> getActiveQrByCustomer(@Param("customerNumber") String customerNumber, @Param("status")Status status);
    QrCode getQrCodeByID(@Param("qrId") String qrId);
    long getSequence();
}
