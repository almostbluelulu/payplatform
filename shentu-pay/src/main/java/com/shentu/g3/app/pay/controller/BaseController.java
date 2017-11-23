/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.app.pay.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shentu.g3.app.pay.route.ApiRouter;
import com.shentu.g3.app.pay.util.Constant;
import com.shentu.g3.app.pay.util.security.AES;
import com.yeepay.g3.facade.yop.ca.dto.DigitalEnvelopeDTO;
import com.yeepay.g3.frame.yop.ca.DigitalEnvelopeUtils;
import com.yeepay.g3.frame.yop.ca.rsa.RSAKeyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

/**
 * 类名称: BaseController <br>
 * 类描述: 公共controller 将共有属性方法统一至此类
 *
 * @author: dongxu.lu
 * @since: 17/9/18 下午2:36
 * @version: 1.0.0
 */
public class BaseController {
    private final  static Log log = LogFactory.getLog(BaseController.class);
    /**
     *获取支付链接
     */
    protected static final String PAY_URI="/rest/v1.0/whitebroad/trade/getpayurl";
    /**
     * 支付回调
     */
    protected static final String PAY_CALLBACK="/rest/v1.0/whitebroad/trade/payCallBack";
    /**
     * 清算回调
     */
    protected static final String CS_CALLBACK="/rest/v1.0/whitebroad/trade/csCallBack";
    /**
     *  台牌信息查询
     */
    protected static final String QR_INFO="/rest/v1.0/whitebroad/qrCode/getQRinfo";

    /**
     * 入网结果回调
     */
    protected static final String ACCOUNT_OPEN_CALLBACK= "/rest/v1.0/whitebroad/account/open/receive";
    /**
     * 根据台签id查询台签信息
     */
    protected static final String QRCODE_BYID= "/rest/v1.0/whitebroad/deskqr/getbyid";
    /**
     * 下单地址
     */
    protected static final String CREATE_ORDER= "/rest/v1.0/whitebroad/trade/scanPay";


    protected static final Gson gson =  new GsonBuilder().disableHtmlEscaping().create();


    @Autowired
    protected ApiRouter apiRouter;

    private static Log LOGGER = LogFactory.getLog(BaseController.class);


    protected String toCommonErrorPage(HttpServletRequest request, String msg) {
        request.setAttribute("msg", msg);
        return "common/error" ;
    }

    protected ModelAndView toCommonErrorPage(String msg) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg",msg);
        modelAndView.setViewName("common/error");
        return modelAndView;
    }

    protected boolean checkSign(String srcString,String sginString){
        String newSign = AES.encryptToBase64(srcString, Constant.STR_AES_KEY);
        if(!sginString.equals(newSign)){
            return false;
        }
        return true;
    }

    protected String getYopResponse(HttpServletRequest request) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String customerIdentification = request.getParameter("customerIdentification");// 此通知对应的appKey
        String yopresponse = request.getParameter("response");
        log.info("appKEY: "+customerIdentification + "payCallBack result:"+yopresponse);
        DigitalEnvelopeDTO dto = new DigitalEnvelopeDTO();
        dto.setCipherText(yopresponse);
        PublicKey yopPublicKey = RSAKeyUtils.string2PublicKey(Constant.YOP_APP_RSA_PUBKEY);
        PrivateKey myPrivateKey = RSAKeyUtils.string2PrivateKey(Constant.YOP_APP_RSA_SECRET);
        dto = DigitalEnvelopeUtils.decrypt(dto, myPrivateKey, yopPublicKey);
        String oprResult = dto.getPlainText();
        log.info("测试YOP回调,plainText:{} "+oprResult);
        return oprResult;
    }

}