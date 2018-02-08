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
package com.js.rms9.common.exceptions;
/**
 * 
 * Objective: This Custom exception class is to handle/wrap exceptions thrown when lambda exceptions were thrown.
 */
public class RMSLambdaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	RMSException re;

	public RMSLambdaException(RMSException re) {
		this.re = re;
	}

	public RMSLambdaException() {
	}

	public RMSException getRSSException() {
		return re;
	}

	public static RMSLambdaException throwWrapped(RMSException re) {
		throw new RMSLambdaException(re);
	}
}
