/**
 * Project Name: RMS v9 Foundation Development
 * @author TCS
 * Copyright @ 2017 - 2018, Sainsbury’s IT 
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of 
 * Sainsbury’s IT * @version 1.0, December 18, 2017
 * 
 * @since v1.0, December 18, 2017
 * 
 * */
package com.js.rms9.common.exphandlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.js.rms9.common.constants.ApplicationErrorMsgConstants;
import com.js.rms9.common.exceptions.RMSUserAuthenticationException;
import com.js.rms9.common.views.RMSViewObject;

/**
 * Objective : This class is to Handle global runtime exceptions i.e exceptions
 * that were not caught in the service/data layers.
 *
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	/**
	 * This method handle Genric runtime exceptions.
	 * 
	 * @param req
	 * @param e
	 * @return RMSViewObject
	 */
	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	public @ResponseBody RMSViewObject defaultErrorHandler(HttpServletRequest req, Exception e) {
		RMSViewObject rmsViewObject = new RMSViewObject();
		rmsViewObject.setStatus(ApplicationErrorMsgConstants.ERROR_MSG);
		rmsViewObject.setErrorKey(ApplicationErrorMsgConstants.UNKNOWN_TECH_ERROR);
		rmsViewObject.setErrorDesc(ApplicationErrorMsgConstants.TECHNICAL_ERROR);
		return rmsViewObject;
	}

	/**
	 * This method handles User Authentication Exception .
	 * 
	 * @param req
	 * @param e
	 * @return RMSViewObject
	 */
	@ExceptionHandler(value = RMSUserAuthenticationException.class)
	public @ResponseBody RMSViewObject authErrorHandler(HttpServletRequest req, Exception e) {
		RMSViewObject rmsViewObject = new RMSViewObject();
		rmsViewObject.setStatus(ApplicationErrorMsgConstants.ERROR_MSG);
		rmsViewObject.setErrorKey(ApplicationErrorMsgConstants.ERROR_AUTH);
		rmsViewObject.setErrorDesc(ApplicationErrorMsgConstants.AUTH_FAILURE);
		return rmsViewObject;
	}

	/**
	 * This method is to handle 404 error
	 * 
	 * @param request
	 * @param e
	 * @return RMSViewObject
	 */
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public @ResponseBody RMSViewObject handleError404(HttpServletRequest request, Exception e) {
		RMSViewObject rmsViewObject = new RMSViewObject();
		rmsViewObject.setStatus(ApplicationErrorMsgConstants.ERROR_MSG);
		rmsViewObject.setErrorKey(ApplicationErrorMsgConstants.ERR_404);
		rmsViewObject.setErrorDesc(ApplicationErrorMsgConstants.ERR_CODE_404_URL + request.getRequestURL() + ApplicationErrorMsgConstants.NOT_FOUND);
		return rmsViewObject;
	}

}