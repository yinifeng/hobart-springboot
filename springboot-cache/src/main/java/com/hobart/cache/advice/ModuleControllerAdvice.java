package com.hobart.cache.advice;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hobart.cache.exception.ServiceException;

/**
 * 异常处理器
 * @author hobart
 *
 */
@ControllerAdvice(basePackages = { "com.hobart.cache" })
public class ModuleControllerAdvice {
	private static final Logger LOG = LoggerFactory.getLogger(ModuleControllerAdvice.class);
	private static final Logger SERVICE_LOG = LoggerFactory.getLogger(ServiceException.class);
	
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ServiceException.class)
	public Map<String, Object> handleServiceException(ServiceException exception){
		String msg="业务受理失败,原因:"+exception.getLocalizedMessage();
		SERVICE_LOG.info(msg);
		Map<String, Object> model=new HashMap<>();
		model.put("code", 500001);//500000表示系统异常，500001表示业务逻辑异常
		model.put("message", msg);
		return model;
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Map<String, Object> handleException(Exception exception){
		String msg="系统异常,原因:"+exception.getLocalizedMessage();
		LOG.error(msg);
		Map<String, Object> model=new HashMap<>();
		model.put("code", 500000);//500000表示系统异常，500001表示业务逻辑异常
		model.put("message", msg);
		return model;
	}
}
