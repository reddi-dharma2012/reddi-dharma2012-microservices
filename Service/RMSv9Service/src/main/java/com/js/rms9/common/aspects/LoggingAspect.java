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

package com.js.rms9.common.aspects;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Objective: This class is the Logging Aspect class to handle logging events on
 * methods specified in point cut.
 * 
 * The List of actions handled by this class are:
 * 
 * 1. Triggers debug level logging event on starting of every method with
 * com.js.rms9 package
 * 
 * 2. Triggers debug level logging event on before exiting of every method with
 * com.js.rms9 package
 * 
 * 3. Triggers error level logging event on any exception thrown with with
 * com.js.rms9 package.
 * 
 */
@Component
@Aspect
public class LoggingAspect {

	static Logger log = Logger.getLogger(LoggingAspect.class);

	/**
	 * Logging event method to log the exceptions thrown in methods within
	 * com.js.rms9 package.
	 * 
	 * @param joinPoint
	 * @param excep
	 */
	@AfterThrowing(pointcut = "within(com.js.rms9..*)", throwing = "excep")
	public void logAfterThrowingException(JoinPoint joinPoint, Exception excep) {
		log.error("Inside" + ":" + joinPoint.getTarget().getClass() + ":" + joinPoint.getSignature().getName());
		log.error("Exception : ", excep);
	}

	/**
	 * Logging event method to log debug level log messages around methods
	 * within com.js.rms9 package.
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("within(com.js.rms9..*)")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		log.debug("Entering into method :: " + pjp.getTarget().getClass() + " : " + pjp.getSignature().getName() + " ");
		log.debug("Method Arguments :: " + Arrays.toString(pjp.getArgs()));

		long start = System.currentTimeMillis();
		Object output = pjp.proceed();

		log.debug("Exiting from method :: " + pjp.getTarget().getClass() + " : " + pjp.getSignature().getName() + "  ");
		long elapsedTime = System.currentTimeMillis() - start;
		log.debug("Execution Time for Class:: " + pjp.getTarget().getClass() + " :: Method ::    "
				+ pjp.getSignature().getName() + "::  " + elapsedTime + " milliseconds.");
		return output;
	}

}