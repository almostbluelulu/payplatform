/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.app.pay.route;

import com.shentu.g3.app.pay.backend.ApiBackend;
import com.shentu.g3.app.pay.backend.handler.AbstractApiHandler;
import com.shentu.g3.app.pay.enums.ExternalSystemUrl;
import com.shentu.g3.app.pay.handler.ApiHandler;
import com.shentu.g3.app.pay.util.RemoteFacadeFactory;
import com.yeepay.g3.facade.whitebroad.dto.api.ApiInfoDTO;
import com.yeepay.g3.facade.whitebroad.facade.WbApiInfoFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @ClassName: EventBasedApiRouter 
 * @Description: 基于事件的api路由器
 * @author yunpeng.pan
 * @date 2017年9月18日 上午11:47:35 
 *
 */
@Component
public class EventBasedApiRouter implements ApiRouter {

    private static final Log LOGGER = LogFactory.getLog(EventBasedApiRouter.class);

    private WbApiInfoFacade wbApiInfoFacade = RemoteFacadeFactory.getService(WbApiInfoFacade.class, ExternalSystemUrl.API_FACADE_URL);
   
    protected Map<String, ApiHandler> handlerRegistry = new ConcurrentHashMap<String, ApiHandler>();

    private static final Timer initializeRouterTimer = new Timer("initialize-router");

    @Autowired
    private ApiBackendFactory apiBackendFactory;

    /**
     * 路由信息初始化，如果失败，会创建一个timerTask不停重试
     */
    @Override
    public void initialize() {
        try {
            doInitialize();
        } catch (Exception e) {
            LOGGER.info("[monitor],eventType:lazy_initialize_api_router_failure", e);
            initializeRouterTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        doInitialize();
                    } catch (Exception e) {
                        LOGGER.info("retry initialize api router failed, continue retrying...", e);
                        return;
                    }
                    LOGGER.info("retry initialize api router success, cancel and quit...");
                    cancel();
                    initializeRouterTimer.cancel();
                }
            }, 0, 1000);
        }
        LOGGER.info("[monitor],eventType:lazy_initialize_api_router_success");
    }

    private void doInitialize() {
        List<ApiInfoDTO> apiDefines = wbApiInfoFacade.queryAllApi();
        for (ApiInfoDTO apiDefine : apiDefines) {
            addRoute(apiDefine);
        }
    }


    @Override
    public void registerRoute(String uri, ApiHandler handler) {
        if (handler == null) {
            handlerRegistry.remove(uri);
            return;
        }
        handlerRegistry.put(uri, handler);
    }

    @Override
    public ApiHandler route(String uri) {
        uri = uri.toLowerCase();
        return handlerRegistry.get(uri);
    }

    private void addRoute(ApiInfoDTO apiDefine) {
        String uri = apiDefine.getApiUri().toLowerCase();
        try {
            ApiBackend backend = apiBackendFactory.createBackend(apiDefine);
            ApiHandler handler = new AbstractApiHandler(apiDefine, backend);
            registerRoute(uri, handler);
            LOGGER.info("load api {} failed caused by no such method:" + uri);
        } catch (NoSuchMethodException e) {
            LOGGER.error("load api {} failed caused by no such method:" + uri, e);
        } catch (Throwable e) {
            LOGGER.error("load api " + uri + " failed with exception", e);
        }
    }

//    public void onEvent(String action, ApiReloadPacket packet) {
//        LOGGER.info("api reload packet received,action:"+ action +",packet:" + packet);
//        if (StringUtils.equals(action, "delete")) {
//            registerRoute(packet.getUri().toLowerCase(), null);
//            LOGGER.info("unload api " + packet.getUri() + " success!");
//            return;
//        }
//        String backendApp = packet.getBackendApp();
//        if (StringUtils.isNotBlank(backendApp)) {
//            backendAppClassLoaderFactory.refreshClassLoader(backendApp);
//            List<ApiDefineDTO> list = apiMgrFacade.queryByBackendApp(backendApp);
//            if (Collections3.isNotEmpty(list)) {
//                for (ApiDefineDTO dto : list) {
//                    addRoute(dto);
//                }
//            }
//            return;
//        }
//        //普通单个api重新加载
//        addRoute(apiMgrFacade.findApi(packet.getId()));
//    }
}