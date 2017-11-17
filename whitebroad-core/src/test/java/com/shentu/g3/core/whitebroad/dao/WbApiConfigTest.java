/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.core.whitebroad.dao;
/**
 * 类名称: WbApiConfigTest <br>
 * 类描述: <br>
 *
 * @author: xxxx.xxx
 * @since: 17/9/28 下午3:06
 * @version: 1.0.0
 */

import com.shentu.g3.core.whitebroad.BaseTest;
import com.shentu.g3.core.whitebroad.entity.WbApiConfigEntity;
import com.shentu.g3.core.whitebroad.repository.WbApiConfigDao;
import com.shentu.g3.core.whitebroad.service.WbApiConfigService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;

/**
 * Created by dongxulu on 17/9/28.
 */
public class WbApiConfigTest extends BaseTest {

    @Autowired
    WbApiConfigService wbApiConfigService;
    @Resource
    WbApiConfigDao wbApiConfigEntityDao;

    @Test
    @Rollback(false)
    public void insert(){
        WbApiConfigEntity wbApiConfigEntity = new WbApiConfigEntity();

        wbApiConfigEntity.setApiHost("http://172.19.100.208:8080/whitebroad-hessian/");
        wbApiConfigEntity.setApiName("根据qrid查询台签信息接口");
//        /rest/v1.0/whitebroad/trade/csCallBack
        wbApiConfigEntity.setApiUri("/rest/v1.0/whitebroad/deskqr/getbyid");
        wbApiConfigEntity.setAvailable(true);
        wbApiConfigEntity.setBackendClass("com.yeepay.g3.facade.whitebroad.facade.QrCodeFacade");
        wbApiConfigEntity.setBackendMethod("getCustomerByQrid(com.yeepay.g3.facade.whitebroad.dto.qrCode.QrCodeRequestDTO)");
        wbApiConfigEntity.setDescription("根据qrid查询台签信息接口");
        wbApiConfigEntity.setNeedSession(false);
        wbApiConfigEntity.setParamValidRule("");
        wbApiConfigEntity.setOperator("");

        wbApiConfigEntityDao.insert(wbApiConfigEntity);

    }

}