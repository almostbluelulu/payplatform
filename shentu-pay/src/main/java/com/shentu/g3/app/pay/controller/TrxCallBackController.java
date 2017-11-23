/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.app.pay.controller;

import com.google.gson.Gson;
import com.shentu.g3.app.pay.dto.OprCallBackResponseParam;
import com.shentu.g3.app.pay.handler.ApiHandler;
import com.shentu.g3.app.pay.http.WFrontResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: TrxCallBackController
 * @Description: TrxCallBackController
 * @author: dongxulu
 * @date: 17/9/29 下午4:05
 * @version: 1.0.0
 */
@Controller
@RequestMapping("opr")
public class TrxCallBackController extends  BaseController{
    private final  static Log log = LogFactory.getLog(TrxCallBackController.class);
    /**
     *付回调
     */
    @RequestMapping("pay")
    @ResponseBody
    public String payCallBack(HttpServletRequest request){
        ApiHandler apiHandler =  apiRouter.route(PAY_CALLBACK);
        String orderid = request.getParameter("orderId");
        WFrontResponse response = null;
        OprCallBackResponseParam oprCallBackResponseParam = null;
        try {
            String oprResult = getYopResponse(request);
            response = apiHandler.handle(oprResult);
            log.info("payCallBack response:"+new Gson().toJson(response));
            Object result = response.getData();
            String resultJson = gson.toJson(result);
            oprCallBackResponseParam = new Gson().fromJson(resultJson,OprCallBackResponseParam.class);
        } catch (Throwable throwable) {
            log.error("payCallBack error",throwable);
            return "fasle";
        }
        return oprCallBackResponseParam.getManageStatus();
    }

    /**
     *清算回调
     */
    @RequestMapping("cs")
    @ResponseBody
    public String csCallBack(HttpServletRequest request){

        ApiHandler apiHandler =  apiRouter.route(CS_CALLBACK);
        WFrontResponse response = null;
        OprCallBackResponseParam oprCallBackResponseParam = null;
        try {
            String oprResult = getYopResponse(request);
            response = apiHandler.handle(oprResult);
            log.info("csCallBack response:"+new Gson().toJson(response));
            Object result = response.getData();
            String resultJson = gson.toJson(result);
            oprCallBackResponseParam = new Gson().fromJson(resultJson,OprCallBackResponseParam.class);
        } catch (Throwable throwable) {
            log.error("csCallBack error",throwable);
            return "fasle";
        }
        return oprCallBackResponseParam.getManageStatus();
    }




}