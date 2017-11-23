/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.app.pay.controller;

import com.shentu.g3.app.pay.enums.ScanType;
import com.shentu.g3.app.pay.dto.QrCodeRequestDTO;
import com.shentu.g3.app.pay.dto.QrCodeResponseDTO;
import com.shentu.g3.app.pay.dto.TrxRequestDTO;
import com.shentu.g3.app.pay.handler.ApiHandler;
import com.shentu.g3.app.pay.http.WFrontResponse;
import com.yeepay.g3.facade.whitebroad.dto.opr.TrxResponseDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: QrCodeController
 * @Description: QrCodeController
 * @author: dongxulu
 * date: 17/9/25 下午3:49
 * @version: 1.0.0
 */
@Controller
@RequestMapping("/qr")
public class QrCodeController extends BaseController{
    private static Log logger = LogFactory.getLog(QrCodeController.class);

    @RequestMapping("/toPay")
    public ModelAndView toDeskQrCode(HttpServletRequest request, @RequestParam("qr_id") String qr_id, @RequestParam("sign") String sign){
        WFrontResponse response = null;
        ModelAndView modelAndView = new ModelAndView();
//        url会将+号替换为空格所以需要重新转换一次
        sign=sign.replace(" ","+");
        if(!checkSign(qr_id,sign)){
            return toCommonErrorPage("签名错误!");
        }
        QrCodeRequestDTO requestDTO = new QrCodeRequestDTO();
        requestDTO.setQrId(qr_id);
        try {
            ApiHandler apiHandler =  apiRouter.route(QRCODE_BYID);
            response = apiHandler.handle(gson.toJson(requestDTO));
            Object result = response.getData();
            String resultJson = gson.toJson(result);
            QrCodeResponseDTO responseDTO = gson.fromJson(resultJson,QrCodeResponseDTO.class);
            modelAndView.addObject("result",responseDTO);
        } catch (Throwable throwable) {
            logger.error("### toDeskQrCode error:",throwable);
            return toCommonErrorPage("获取台签信息异常");
        }
        modelAndView.setViewName("standard/pay");
        return modelAndView;
    }

    @RequestMapping("/doPay")
    public String doPay(HttpServletRequest request,TrxRequestDTO trxRequestDTO){
        ApiHandler apiHandler =  apiRouter.route(CREATE_ORDER);
        String payUrl = null ;
        WFrontResponse response = null;
        trxRequestDTO.setScanType(ScanType.DESK_QR);
        try {
            response = apiHandler.handle(gson.toJson(trxRequestDTO));
            Object result = response.getData();
            String resultJson = gson.toJson(result);
            TrxResponseDTO responseDTO = gson.fromJson(resultJson,TrxResponseDTO.class);
            logger.info("return "+gson.toJson(responseDTO));
            payUrl = responseDTO.getPayUrl();
        } catch (Throwable throwable) {
            logger.error("### toDeskQrCode error:",throwable);
            return toCommonErrorPage(request,"下单异常");
        }
        return "redirect:" + payUrl;
    }

}