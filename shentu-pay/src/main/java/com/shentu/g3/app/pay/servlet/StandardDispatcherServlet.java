package com.shentu.g3.app.pay.servlet;

import com.shentu.g3.app.pay.exception.WFSysException;
import com.shentu.g3.app.pay.util.WFAuthUtil;
import com.shentu.g3.app.pay.chain.InterceptorChain;
import com.shentu.g3.app.pay.context.StandardContext;
import com.shentu.g3.app.pay.http.WFrontResponse;
import com.shentu.g3.app.pay.route.ApiRouter;
import com.shentu.g3.app.pay.util.DataConvertUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 * @ClassName: StandardDispatcherServlet 
 * @Description: wb-front入口servlet
 * @author yunpeng.pan
 * @date 2017年9月18日 下午4:20:02 
 *
 */
public class StandardDispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 7918717089758986614L;

	private static final Log logger = LogFactory.getLog(StandardDispatcherServlet.class);

    @Autowired
    private InterceptorChain interceptorChain;

    @Autowired
    private ApiRouter apiRouter;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        apiRouter.initialize();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //stopWatch
        long startTime = System.currentTimeMillis();
        StandardContext context = new StandardContext(request, response);
        String requestId = context.getRequestId();
        String token = context.getUserToken();
        WFrontResponse result = null;
        String resultStr = null;
        /*接受请求，打印接受请求日志，便于日志分析，视为认证基数（统计各种指标时，以此为分母）*/
        logger.info("[monitor],eventType:request,context:" + context);
        try {
            result = interceptorChain.getHead().intercept(context);
        } catch (WFSysException we) {
            logger.info("[monitor],eventType:request,status:failure, " + context + ",costTime:" + (System.currentTimeMillis() - startTime) + ",errorMsg:" + we.getMessage());
        	logger.error("error handle request:" + requestId, we);
            result = new WFrontResponse(we.getDefineCode(), we.getMessage());
            return;
        } catch (Throwable e) {
            /*请求失败，打印失败事件日志，并打印简要消息(包含异常)，便于日志分析*/
            logger.info("[monitor],eventType:request,status:failure, " + context + ",costTime:" + (System.currentTimeMillis() - startTime) + ",errorMsg:" + e.getMessage());
            logger.error("error handle request:" + requestId, e);
            result = new WFrontResponse("L10001", "系统异常");
            return;
        } finally {
            try {
            	response.setCharacterEncoding("UTF-8");
                response.setContentType("text/json");
                String resultDate;
                // 无token需要加密data
                if(StringUtils.isBlank(token) && result.getData() != null) {
                	resultDate = WFAuthUtil.encryptSimple(DataConvertUtil.toJson(result.getData()), context.getAesKey());
                } else {
                	resultDate = DataConvertUtil.toJson(result.getData());
                }
                result.setData(resultDate);
                resultStr = DataConvertUtil.toJson(result);
                OutputStream out = response.getOutputStream();
                out.write(resultStr.getBytes("UTF-8"));
                out.flush();
                out.close();
            } catch (Exception e) {
                logger.warn("response close error.", e);
            }
        }
         /*请求成功，打印成功事件日志，并打印简要消息，便于日志分析*/
        logger.info("[monitor],eventType:request,status:success, context:" + context + ",result:" + resultStr + ",costTime:" + (System.currentTimeMillis() - startTime));
    }
}
