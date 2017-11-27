package com.shentu.g3.core.whitebroad.aop;

import com.shentu.g3.core.whitebroad.util.LoggerUtil;
import com.shentu.g3.facade.whitebroad.dto.BaseResponse;
import com.shentu.g3.facade.whitebroad.dto.ResponseStatus;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Description: facade请求日志切面
 * Author: jiawen.huang
 * Date: 16/9/14
 * Time: 11:39
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
@Aspect
@Order(1)
public class InnerFacadeLogAdvice {

	private static Log LOGGER = LogFactory.getLog(InnerFacadeLogAdvice.class);

	@Around(value = "execution(* com.yeepay.g3.facade.whitebroad.facade..*.*(..))")
	public Object facadeAround(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object[] args = joinPoint.getArgs();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		String methodName = methodSignature.getDeclaringTypeName() + "." + methodSignature.getName();
		Object result = null;
		try {
			LOGGER.info("[wb_sys] - [入参] - [" + methodName + "] - [" + LoggerUtil.argsToString(args) + "]");
			result = joinPoint.proceed();
		} catch (Throwable e) {
			if (result == null) {
				result = method.getReturnType().newInstance();
			}
			handelException("wb_sys", methodName, (BaseResponse) result, e);
		} finally {
			long time = (System.currentTimeMillis() - startTime);
			LOGGER.info("[wb_sys] - [用时] - [" + methodName + "] - 耗时[" + time + "]毫秒");
		}
		LOGGER.info("[wb_sys] - [返回] - [" + methodName + "] - [" + LoggerUtil.argsToString(result) + "]");
		return result;
	}

	@Around(value = "execution(* com.yeepay.g3.core.whitebroad.task..*.*(..))")
	public Object taskAround(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object[] args = joinPoint.getArgs();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		String methodName = methodSignature.getDeclaringTypeName() + "." + methodSignature.getName();
		Object result = null;
		try {
			LOGGER.info("[wb_task] - [入参] - [" + methodName + "] - [" + LoggerUtil.argsToString(args) + "]");
			result = joinPoint.proceed();
		} catch (Throwable e) {
			if (result == null) {
				result = method.getReturnType().newInstance();
			}
			handelException("wb_task", methodName, (BaseResponse) result, e);
		} finally {
			long time = (System.currentTimeMillis() - startTime);
			LOGGER.info("[wb_task] - [用时] - [" + methodName + "] - 耗时[" + time + "]毫秒");
		}
//		LOGGER.info("[wb_task] - [返回] - [" + methodName + "] - [" + LoggerUtil.argsToString(result) + "]");
		return result;
	}

	private void handelException(String logPrefix, String method, BaseResponse result, Throwable e) {
		BaseResponse responseDTO = result;
		String errorCode;
		String errorMsg;
		ExceptionType exceptionType = ExceptionType.BIZ_EXCEPTION;
		if (e instanceof WbSysException) {
			WbSysException bizException = (WbSysException) e;
			errorCode = bizException.getDefineCode();
			errorMsg = bizException.getMessage();
		} else if (e instanceof IllegalArgumentException) {
			errorCode = ErrorCode.PARAM_EXCEPTION;
			errorMsg = e.getMessage();
			exceptionType = ExceptionType.PARAM_EXCEPTION;
		} else {
			errorCode = ErrorCode.SYSTEM_EXCEPTION;
			errorMsg = "系统异常";
			exceptionType = ExceptionType.SYS_EXCEPTION;
		}
		logError(logPrefix, method, e, exceptionType, errorCode, errorMsg);
		setFailResponse(responseDTO, errorCode, errorMsg);
	}

	private void logError(String logPrefix, String methodName, Throwable e, ExceptionType exceptionType,
						  String errorCode, String errorMsg) {
		LOGGER.warn("[" + logPrefix + "] - [" + exceptionType.description + "] - [" + methodName + "] - "
				+ "errorCode:[" + errorCode + "], " + "errorMsg:[" + errorMsg + "]", e);
	}

	private void setFailResponse(BaseResponse responseDTO, String errorCode, String errorMsg) {
		responseDTO.setStatus(ResponseStatus.FAILURE);
		responseDTO.setErrCode(errorCode);
		responseDTO.setErrMsg(errorMsg);
	}

	private enum ExceptionType {

		PARAM_EXCEPTION("参数异常"),
		BIZ_EXCEPTION("业务异常"),
		SYS_EXCEPTION("系统异常");

		private final String description;

		ExceptionType(String description) {
			this.description = description;
		}
	}

}
