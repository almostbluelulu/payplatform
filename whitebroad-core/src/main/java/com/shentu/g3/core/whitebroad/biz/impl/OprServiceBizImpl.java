/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.core.whitebroad.biz.impl;
/**
 * 类名称: OprServiceImpl <br>
 * 类描述: <br>
 *
 * @author: xxxx.xxx
 * @since: 17/9/20 上午11:35
 * @version: 1.0.0
 */

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.shentu.g3.core.whitebroad.biz.AbstractBiz;
import com.shentu.g3.core.whitebroad.biz.OprServiceBiz;
import com.shentu.g3.core.whitebroad.entity.trade.Order;
import com.shentu.g3.core.whitebroad.util.Constant;
import com.shentu.g3.core.whitebroad.util.RemoteFacadeFactory;
import com.shentu.g3.core.whitebroad.util.security.Md5Utils;
import com.shentu.g3.facade.whitebroad.dto.cashier.CashierParam;
import com.shentu.g3.facade.whitebroad.dto.opr.OprOrderParamRequestDTO;
import com.shentu.g3.facade.whitebroad.dto.opr.OprOrderParamResponseDTO;
import com.shentu.g3.facade.whitebroad.enumtype.ExternalSystem;
import com.shentu.g3.facade.whitebroad.enumtype.trx.UserType;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;
import com.yeepay.g3.facade.yop.ca.dto.DigitalSignatureDTO;
import com.yeepay.g3.facade.yop.ca.enums.CertTypeEnum;
import com.yeepay.g3.facade.yop.ca.enums.DigestAlgEnum;
import com.yeepay.g3.frame.yop.ca.DigitalEnvelopeUtils;
import com.yeepay.g3.frame.yop.ca.rsa.RSAKeyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.shentu.g3.core.whitebroad.util.Constant.CASHIER_HOST;

@Service
public class OprServiceBizImpl extends AbstractBiz implements OprServiceBiz {
    private final static Log log = LogFactory.getLog(OprServiceBizImpl.class);
    @Override
    public OprOrderParamResponseDTO creatOprOrder(OprOrderParamRequestDTO requestDTO) {
        Map<String, Object> params ;
        params = getOprParam(requestDTO);
        Map resultMap = (Map) RemoteFacadeFactory.getYopService(ExternalSystem.OPR_SYS,params);
        OprOrderParamResponseDTO resultParam =  new Gson().fromJson(new Gson().toJson(resultMap), OprOrderParamResponseDTO.class);
        if(!resultParam.getCode().equals("OPR00000")){
            //透传订单处理器错误码与错误信息
            throw new WbSysException(resultParam.getCode(),resultParam.getMessage());
        }
        return resultParam;
    }

    @Override
    public Map<String, Object> getOprParam(OprOrderParamRequestDTO requestDTO) {
         Map<String, Object> map = null;
        try {
            map = new Gson().fromJson(new Gson().toJson(requestDTO),LinkedHashMap.class);
            String srcHmac = requestDTO.getSingString();
            Map<String,Object> merchatMap = new HashMap<>();
            merchatMap.put("parentMerchantNo",requestDTO.getParentMerchantNo());
            merchatMap.put("merchantNo",requestDTO.getMerchantNo());
            Map merchantKeyMap = (Map)RemoteFacadeFactory.getYopService(ExternalSystem.QRUERY_MERCHANT_KEY,merchatMap);
            String merchatKey = (String)merchantKeyMap.get("merHmacKey");
            if(StringUtils.isEmpty(merchatKey)){
                throw new WbSysException(ErrorCode.QUERY_YEEPAY_KEY_EXCEPTION);
            }
            //
            map.put("hmac", Md5Utils.encoderHmacSha256(srcHmac,merchatKey));
        } catch (JsonSyntaxException e) {
            throw new WbSysException(ErrorCode.QUERY_YEEPAY_GSONFORMAT_EXCEPTION,e);
        }
        return map ;
    }

    @Override
    public String getPayUrl(Order order) {
        StringBuffer urlBuilder = new StringBuffer();
//        User2PrivateKey user2PrivateKey = user2PrivateKeyService.getByUserNumber(order.getUserNumber());
        CashierParam param = initCashierParam(order);
        String sign = getSign(param,Constant.YOP_APP_RSA_SECRET);
        urlBuilder.append(CASHIER_HOST);
        urlBuilder.append("?");
        urlBuilder.append(param.getSingString());
        urlBuilder.append("&sign=");
        urlBuilder.append(sign);
        return urlBuilder.toString();
    }


    /**
     * 初始化收银台参数
     */
    private CashierParam initCashierParam(Order order){
        CashierParam param = new CashierParam();
        //收银台签名商编,非收单商编,很别扭啊,验签的商编跟收单是没关系的
        param.setMerchantNo(Constant.MERCHANT_NUMBER);
        param.setToken(order.getToken());
        if(null != order.getPayType()){
            param.setDirectPayType(order.getPayType().toString());
        }
        //时间戳  要求到秒级
        param.setTimestamp(String.valueOf(System.currentTimeMillis()/1000));
        param.setUserNo(order.getUserNumber());
        param.setUserType(UserType.USER_ID.toString());
        return param;
    }

    /**
     * 获取签名
     * @param param
     * @return
     * @throws IllegalAccessException
     */
    private String getSign(CashierParam param,String privateKey) throws WbSysException{
        String srcSign = param.getSingString();
        PrivateKey isvPrivateKey;
        String appKey = Constant.YOP_APP_KEY;
        try {
            isvPrivateKey =  RSAKeyUtils.string2PrivateKey(privateKey);
        } catch (InvalidKeySpecException e) {
            throw new WbSysException(ErrorCode.JPUSH_ILLEGAL_ARGUMENT,e);
        } catch (NoSuchAlgorithmException e) {
            throw new WbSysException(ErrorCode.JPUSH_ILLEGAL_ARGUMENT,e);
        }
        log.info("####srcSign:"+srcSign);
        DigitalSignatureDTO digitalSignatureDTO = new DigitalSignatureDTO();
        digitalSignatureDTO.setAppKey(appKey);
        digitalSignatureDTO.setCertType(CertTypeEnum.RSA2048);
        digitalSignatureDTO.setDigestAlg(DigestAlgEnum.SHA256);
        digitalSignatureDTO.setPlainText(srcSign);
        String sign = DigitalEnvelopeUtils.sign0(digitalSignatureDTO,isvPrivateKey);
        return sign;
    }



}