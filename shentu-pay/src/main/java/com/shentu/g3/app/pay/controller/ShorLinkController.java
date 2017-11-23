/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.app.pay.controller;

import com.shentu.g3.app.pay.dto.GetCashierUrlResponseDTO;
import com.shentu.g3.app.pay.enums.ResponseStatus;
import com.shentu.g3.app.pay.handler.ApiHandler;
import com.shentu.g3.app.pay.dto.GetCashierUrlRequestDTO;
import com.shentu.g3.app.pay.http.WFrontResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ShorLinkController
 * @Description: 短链服务,直接转发至支付处理器
 * @author: dongxulu
 * @date: 17/9/19 下午2:22
 * @version: 1.0.0
 */
@Controller
@RequestMapping("/link")
public class ShorLinkController extends BaseController {
    private static final Log logger = LogFactory.getLog(ShorLinkController.class) ;
    /**
     * 主扫(标准收银台)短链接跳转
     * @param trxid       paymentId
     * @retun
     */
    @RequestMapping("/pay")
    public String payLink(@RequestParam("trxid") String trxid, HttpServletRequest request) {
        try {
            String cashierUrl = "";
            try {
            ApiHandler apiHandler =  apiRouter.route(PAY_URI);
            WFrontResponse response = null;
                logger.info("收到短链请求,paymentId={}:"+trxid);
                GetCashierUrlRequestDTO getCashierUrlRequestDTO = new GetCashierUrlRequestDTO();
                getCashierUrlRequestDTO.setOrderId(trxid);
                response = apiHandler.handle(gson.toJson(getCashierUrlRequestDTO));
                logger.info("###payLink return:"+gson.toJson(response));
                Object result = response.getData();
                String resultJson = gson.toJson(result);
                GetCashierUrlResponseDTO getCashierUrlResponseDTO =  gson.fromJson(resultJson,GetCashierUrlResponseDTO.class);
                if(ResponseStatus.SUCCESS.equals(getCashierUrlResponseDTO.getStatus())){
                    cashierUrl = getCashierUrlResponseDTO.getPayUrl();
                }
            } catch (Throwable throwable) {
                logger.error("apiHandler.handle exception ",throwable);
                return toCommonErrorPage(request,"系统异常");
            }


            return "redirect:" + cashierUrl;
        } catch (Exception e) {
            logger.error("payLink Exception",e);
            return toCommonErrorPage(request,"获取支付链接失败") ;
        }
    }

    private Map<String,Object> getParam(){
        Map<String,Object> param = new HashMap<String,Object>();

        return param;
    }



}