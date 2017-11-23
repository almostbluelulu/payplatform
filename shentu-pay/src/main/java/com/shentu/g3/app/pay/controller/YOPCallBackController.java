package com.shentu.g3.app.pay.controller;

import com.google.gson.Gson;
import com.shentu.g3.app.pay.handler.ApiHandler;
import com.shentu.g3.app.pay.http.WFrontResponse;
import com.yeepay.g3.facade.yop.ca.dto.DigitalEnvelopeDTO;
import com.yeepay.g3.frame.yop.ca.DigitalEnvelopeUtils;
import com.yeepay.g3.frame.yop.ca.rsa.RSAKeyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.PrivateKey;
import java.security.PublicKey;

import static com.shentu.g3.app.pay.util.Constant.merPrivateKeyStr;
import static com.shentu.g3.app.pay.util.Constant.yeepayPublicKeyStr;

/**
 * Description: YOP回调
 * Author: wei.li
 * Date: 17/10/13
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Controller
@RequestMapping("/yop")
public class YOPCallBackController extends BaseController{

    private final static Log log = LogFactory.getLog(YOPCallBackController.class);

    /**
     * 入网回调
     *
     * @param request
     * @return
     */
    @RequestMapping("/open")
    @ResponseBody
    public String payCallBack(HttpServletRequest request){
        ApiHandler apiHandler =  apiRouter.route(ACCOUNT_OPEN_CALLBACK);
        WFrontResponse wFrontResponse = null;
        String result = "FALSE";
        try {
            String r = request.getParameter("response");
            log.info("accountOpenCallBack r:"+ r);
            DigitalEnvelopeDTO dto = new DigitalEnvelopeDTO();
            dto.setCipherText(r);
            PublicKey yeepayPublicKey = RSAKeyUtils.string2PublicKey(yeepayPublicKeyStr);
            PrivateKey merPrivateKey = RSAKeyUtils.string2PrivateKey(merPrivateKeyStr);
            dto = DigitalEnvelopeUtils.decrypt(dto, merPrivateKey, yeepayPublicKey);
            wFrontResponse = apiHandler.handle(dto.getPlainText());
            log.info("accountOpenCallBack wFrontResponse:"+ new Gson().toJson(wFrontResponse));
            if ("0000".equals(wFrontResponse.getStatus().getCode())){
                result = "SUCCESS";
            }
        } catch (Throwable throwable) {
            log.error("accountOpenCallBack error",throwable);
            return "FALSE";
        }
        return result;
    }
}
