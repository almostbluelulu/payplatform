package com.shentu.g3.app.pay.chain.intercept;

import com.google.common.base.Stopwatch;
import com.shentu.g3.app.pay.enums.ExternalSystemUrl;
import com.shentu.g3.app.pay.exception.WFSysException;
import com.shentu.g3.app.pay.util.DateUtils;
import com.shentu.g3.app.pay.util.RemoteFacadeFactory;
import com.shentu.g3.app.pay.util.WFAuthUtil;
import com.shentu.g3.app.pay.context.Context;
import com.shentu.g3.app.pay.http.WFrontResponse;
import com.shentu.g3.app.pay.util.DataConvertUtil;
import com.yeepay.g3.facade.whitebroad.dto.BaseResponse;
import com.yeepay.g3.facade.whitebroad.dto.ResponseStatus;
import com.yeepay.g3.facade.whitebroad.dto.api.ApiInfoDTO;
import com.yeepay.g3.facade.whitebroad.dto.auth.UserAuthDTO;
import com.yeepay.g3.facade.whitebroad.facade.WbApiInfoFacade;
import com.yeepay.g3.facade.whitebroad.facade.WbUserAuthFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @ClassName: AuthenticateInterceptor 
 * @Description: 通过解密和验签来验证用户身份
 * @author yunpeng.pan
 * @date 2017年9月19日 上午11:44:20 
 *
 */
@Component("authenticateInterceptor")
public class AuthenticateInterceptor extends AbstractInterceptor {

	private WbApiInfoFacade wbApiInfoFacade = RemoteFacadeFactory.getService(WbApiInfoFacade.class, ExternalSystemUrl.API_FACADE_URL);
	private WbUserAuthFacade wbUserAuthFacade = RemoteFacadeFactory.getService(WbUserAuthFacade.class, ExternalSystemUrl.USERAUTH_FACADE_URL);
	
    @SuppressWarnings("unchecked")
	@Override
    public WFrontResponse onIntercept(Context context) throws Throwable {
        Stopwatch stopwatch = new Stopwatch().start();
//        logger.info("[monitor],eventType:authenticate, context:" + context);

		Map<String, Object> dataMap = null;
        String data = context.getData();
        String apiUri = context.getApiUri();
        String userToken = context.getUserToken();
        ApiInfoDTO apiInfoDto = wbApiInfoFacade.findApiInfoByUri(apiUri);
        assertSuccess(apiInfoDto);
        if(apiInfoDto.getNeedSession()) {
        	// 1. 需要登录的api，验证token
        	UserAuthDTO userAuthDto = wbUserAuthFacade.findUserAuthInfo(userToken);
        	assertSuccess(userAuthDto);
        	try {
				dataMap = DataConvertUtil.fromJson(data, Map.class);
			} catch (Exception e) {
				throw new WFSysException("1000201", "非法请求参数");
			}
			if (StringUtils.isBlank(userAuthDto.getUserNumber())) {
				// token不存在
				throw new WFSysException("1000202", "用户没有权限");
			}
			if (!userAuthDto.getUserNumber().equals(dataMap.get("userNumber"))) {
				// token、userNumber不匹配
				throw new WFSysException("1000203", "用户非法访问");
			}
			if (DateUtils.compareDate(new Date(), userAuthDto.getExpireTime(), Calendar.SECOND) > 0) {
				// token失效
				throw new WFSysException("1000204", "用户登录状态失效");
			}
		} else {
			// 非登录，参数解密验签,并保存请求中的aesKey
        	String decryptData = WFAuthUtil.decrypt(data, context);
        	context.setData(decryptData);
        }
		
        logger.info("[monitor],eventType:authenticate,status:success,context:" + context + ",timeElapse:" + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return getNext().intercept(context);
    }
    
    private void assertSuccess(BaseResponse response) {
    	if(response.getStatus() != ResponseStatus.SUCCESS) {
    		throw new WFSysException(response.getErrCode(), response.getErrMsg());
    	}
    }
}
