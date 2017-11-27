package com.shentu.g3.hessian.servlet;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Servlet 为w-front返回接口类的定义
 */
public class OutPutClassServlet extends HttpServlet {
	
	private static final Log logger = LogFactory.getLog(OutPutClassServlet.class);
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutPutClassServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath = getServletContext().getContextPath();
		String className = request.getRequestURI().replace(contextPath + "/class/", "");
		logger.info("get class file start:" + className);
		InputStream stream = this.getClass().getClassLoader().getResourceAsStream(className);
		if(stream == null) {
			logger.info("class not exist:" + className);
		}
		response.getOutputStream().write(IOUtils.toByteArray(stream));
		response.flushBuffer();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
